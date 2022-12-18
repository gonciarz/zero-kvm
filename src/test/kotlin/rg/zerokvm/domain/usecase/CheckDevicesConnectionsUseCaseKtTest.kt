package rg.zerokvm.domain.usecase

import arrow.core.left
import arrow.core.partially1
import arrow.core.right
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.annotation.Ignored
import io.kotest.core.spec.style.FreeSpec
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import rg.zerokvm.domain.model.ConnectionStatus
import rg.zerokvm.domain.model.Device
import rg.zerokvm.domain.model.LocalDevices
import rg.zerokvm.domain.port.api.CheckDevicesConnections
import rg.zerokvm.domain.port.api.CheckDevicesConnectionsError
import rg.zerokvm.domain.port.spi.CheckKeyboardConnection
import rg.zerokvm.domain.port.spi.CheckMonitorConnection
import rg.zerokvm.domain.port.spi.CheckMouseConnection
import rg.zerokvm.domain.port.spi.KeyboardError

@Ignored
class CheckDevicesConnectionsUseCaseKtTest : FreeSpec({

    val checkMonitorConnection: CheckMonitorConnection = mockk()
    val checkKeyboardConnection: CheckKeyboardConnection = mockk()
    val checkMouseConnection: CheckMouseConnection = mockk()

    val checkDevicesConnections: CheckDevicesConnections = ::checkDevicesConnections
        .partially1(checkMonitorConnection)
        .partially1(checkKeyboardConnection)
        .partially1(checkMouseConnection)

    beforeContainer {
        clearAllMocks()
    }

    "positive scenario" {
        // given
        coEvery { checkMonitorConnection.invoke() } returns ConnectionStatus.CONNECTED.right()
        coEvery { checkKeyboardConnection.invoke() } returns ConnectionStatus.CONNECTED.right()
        coEvery { checkMouseConnection.invoke() } returns ConnectionStatus.NOT_FOUND.right()

        // when
        val result = checkDevicesConnections()

        // then
        result.shouldBeRight(
            LocalDevices(
                monitor = ConnectionStatus.CONNECTED,
                keyboard = ConnectionStatus.CONNECTED,
                mouse = ConnectionStatus.NOT_FOUND,
            )
        )
    }

    "negative scenario" - {

        "should return execution error when check connection execution fails" {
            // given
            coEvery { checkMonitorConnection.invoke() } returns ConnectionStatus.CONNECTED.right()
            coEvery { checkKeyboardConnection.invoke() } returns KeyboardError.ExecutionError.left()
            coEvery { checkMouseConnection.invoke() } returns ConnectionStatus.CONNECTED.right()

            // when
            val result = checkDevicesConnections()

            // then
            result.shouldBeLeft(CheckDevicesConnectionsError.ExecutionError(Device.KEYBOARD))
        }
    }

})
