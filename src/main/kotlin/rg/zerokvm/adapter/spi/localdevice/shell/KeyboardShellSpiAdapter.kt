package rg.zerokvm.adapter.spi.localdevice.shell

import arrow.core.Either
import kotlinx.coroutines.delay
import mu.KotlinLogging
import rg.zerokvm.domain.model.Computer
import rg.zerokvm.domain.model.ConnectionStatus
import rg.zerokvm.domain.port.spi.KeyboardError
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class KeyboardShellSpiAdapter {

    private val logger = KotlinLogging.logger {}
    suspend fun assign(computer: Computer): Either<KeyboardError, Unit> {
        delay(100)
        return Either.Left(KeyboardError.ExecutionError)
    }

    suspend fun queryConnection(): Either<KeyboardError, ConnectionStatus> {
        delay(100)
        return Either.Left(KeyboardError.ExecutionError)
    }
}