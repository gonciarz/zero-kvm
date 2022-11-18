package rg.zerokvm.domain.port.api

import arrow.core.Either
import rg.zerokvm.domain.model.Computer

typealias BroadcastSwitchActiveComputer = suspend (Computer) -> Either<BroadcastSwitchActiveComputerError, Unit>

sealed interface BroadcastSwitchActiveComputerError {
    object PublisherError : BroadcastSwitchActiveComputerError
}
