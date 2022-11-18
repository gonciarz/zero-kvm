package rg.zerokvm.adapter.spi.localdevice.shell

import arrow.core.Either
import kotlinx.coroutines.delay
import rg.zerokvm.domain.model.Computer
import rg.zerokvm.domain.model.ConnectionStatus
import rg.zerokvm.domain.port.spi.MonitorError
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class MonitorShellSpiAdapter {
    suspend fun assign(computer: Computer): Either<MonitorError, Unit> {
        delay(100)
        return Either.Left(MonitorError.ExecutionError)
    }

    suspend fun queryConnection(): Either<MonitorError, ConnectionStatus> {
        delay(100)
        return Either.Left(MonitorError.ExecutionError)
    }

}