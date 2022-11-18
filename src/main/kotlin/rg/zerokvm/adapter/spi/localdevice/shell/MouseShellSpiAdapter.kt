package rg.zerokvm.adapter.spi.localdevice.shell

import arrow.core.Either
import kotlinx.coroutines.delay
import rg.zerokvm.domain.model.Computer
import rg.zerokvm.domain.model.ConnectionStatus
import rg.zerokvm.domain.port.spi.MouseError
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class MouseShellSpiAdapter {
    suspend fun assign(computer: Computer): Either<MouseError, Unit> {
        delay(100)
        return Either.Left(MouseError.ExecutionError)
    }

    suspend fun queryConnection(): Either<MouseError, ConnectionStatus> {
        delay(100)
        return Either.Left(MouseError.ExecutionError)
    }
}