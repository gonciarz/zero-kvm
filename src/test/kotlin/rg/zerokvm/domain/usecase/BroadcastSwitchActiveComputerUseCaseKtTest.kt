package rg.zerokvm.domain.usecase

import arrow.core.left
import arrow.core.partially1
import arrow.core.right
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.FreeSpec
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import rg.zerokvm.domain.model.Computer
import rg.zerokvm.domain.port.api.BroadcastSwitchActiveComputer
import rg.zerokvm.domain.port.api.BroadcastSwitchActiveComputerError
import rg.zerokvm.domain.port.spi.PublishSwitchActiveComputer
import rg.zerokvm.domain.port.spi.PublisherError

class BroadcastSwitchActiveComputerUseCaseKtTest : FreeSpec({

    val publishSwitchActiveComputer: PublishSwitchActiveComputer = mockk()
    val broadcastSwitchActiveComputer: BroadcastSwitchActiveComputer = ::broadcastSwitchActiveComputer
        .partially1(publishSwitchActiveComputer)

    beforeContainer {
        clearAllMocks()
    }

    "positive scenario" {
        // given
        coEvery { publishSwitchActiveComputer.invoke(any()) } returns Unit.right()

        // when
        val result = broadcastSwitchActiveComputer(Computer.COMPUTER_1)

        // then
        result.shouldBeRight()
    }

    "negative scenario" - {

        "should return publisher error when publisher fails" {
            // given
            coEvery { publishSwitchActiveComputer.invoke(any()) } returns PublisherError.ConnectionError.left()

            // when
            val result = broadcastSwitchActiveComputer(Computer.COMPUTER_1)

            // then
            result.shouldBeLeft(BroadcastSwitchActiveComputerError.PublisherError)
        }
    }

})
