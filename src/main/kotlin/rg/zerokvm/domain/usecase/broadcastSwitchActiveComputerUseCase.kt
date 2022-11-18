package rg.zerokvm.domain.usecase

import arrow.core.Either
import rg.zerokvm.domain.model.Computer
import rg.zerokvm.domain.port.api.BroadcastSwitchActiveComputerError
import rg.zerokvm.domain.port.spi.PublishSwitchActiveComputer

suspend fun broadcastSwitchActiveComputer(
    publishSwitchActiveComputer: PublishSwitchActiveComputer,
    computer: Computer,
): Either<BroadcastSwitchActiveComputerError, Unit> {
    return publishSwitchActiveComputer(computer)
        .mapLeft { BroadcastSwitchActiveComputerError.PublisherError }
}