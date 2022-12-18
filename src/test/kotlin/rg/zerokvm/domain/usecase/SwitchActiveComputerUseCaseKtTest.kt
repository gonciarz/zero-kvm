package rg.zerokvm.domain.usecase

import arrow.core.left
import arrow.core.partially1
import arrow.core.right
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.names.TestName
import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.test.createTestName
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import rg.zerokvm.domain.model.Computer
import rg.zerokvm.domain.model.Device
import rg.zerokvm.domain.model.SwitchActiveComputerCommand
import rg.zerokvm.domain.port.api.SwitchActiveComputer
import rg.zerokvm.domain.port.api.SwitchActiveComputerError
import rg.zerokvm.domain.port.spi.AssignKeyboard
import rg.zerokvm.domain.port.spi.AssignMonitor
import rg.zerokvm.domain.port.spi.AssignMouse
import rg.zerokvm.domain.port.spi.KeyboardError
import rg.zerokvm.domain.port.spi.PublishDeviceAssignedEvent
import rg.zerokvm.domain.port.spi.PublisherError
import java.time.LocalDateTime
import java.util.*

class SwitchActiveComputerUseCaseKtTest : FreeSpec({

//    TestName("aaa")

//    val testNameCase = "aaa"

    val assignMonitor: AssignMonitor = mockk()
    val assignKeyboard: AssignKeyboard = mockk()
    val assignMouse: AssignMouse = mockk()
    val publishDeviceAssignedEvent: PublishDeviceAssignedEvent = mockk()

    val switchActiveComputer: SwitchActiveComputer = ::switchActiveComputer
        .partially1(assignMonitor)
        .partially1(assignKeyboard)
        .partially1(assignMouse)
        .partially1(publishDeviceAssignedEvent)

    val command = SwitchActiveComputerCommand(
        commandId = UUID.randomUUID(),
        createdAt = LocalDateTime.now(),
        computer = Computer.COMPUTER_1,
    )

    beforeContainer {
        clearAllMocks()
    }

    "positive scenario" {
        // given
        coEvery { assignMonitor.invoke(any()) } returns Unit.right()
        coEvery { assignKeyboard.invoke(any()) } returns Unit.right()
        coEvery { assignMouse.invoke(any()) } returns Unit.right()
        coEvery { publishDeviceAssignedEvent.invoke(any()) } returns Unit.right()

        // when
        val result = switchActiveComputer(command)

        // then
        result.shouldBeRight(Unit)
    }

    "negative scenario".config(enabled = false) - {

        "should return device assignment error when assigning device fails" {

            // given
            coEvery { assignMonitor.invoke(any()) } returns Unit.right()
            coEvery { assignKeyboard.invoke(any()) } returns KeyboardError.ExecutionError.left()
            coEvery { assignMouse.invoke(any()) } returns Unit.right()
            coEvery { publishDeviceAssignedEvent.invoke(any()) } returns Unit.right()

            // when
            val result = switchActiveComputer(command)

            // then
            result.shouldBeLeft(SwitchActiveComputerError.DeviceAssignmentError(Device.KEYBOARD))
        }

        "should return publisher error when publisher fails" {

            // given
            coEvery { assignMonitor.invoke(any()) } returns Unit.right()
            coEvery { assignKeyboard.invoke(any()) } returns Unit.right()
            coEvery { assignMouse.invoke(any()) } returns Unit.right()
            coEvery { publishDeviceAssignedEvent.invoke(any()) } returns PublisherError.ConnectionError.left()

            // when
            val result = switchActiveComputer(command)

            // then
            result.shouldBeLeft(SwitchActiveComputerError.NotificationError(Device.KEYBOARD))
        }
    }

})
