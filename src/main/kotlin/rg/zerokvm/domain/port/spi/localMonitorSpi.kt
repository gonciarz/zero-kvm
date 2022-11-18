package rg.zerokvm.domain.port.spi

import arrow.core.Either
import rg.zerokvm.domain.model.Computer
import rg.zerokvm.domain.model.ConnectionStatus

typealias AssignMonitor = suspend (Computer) -> Either<MonitorError, Unit>
typealias CheckMonitorConnection = suspend () -> Either<MonitorError, ConnectionStatus>

sealed interface MonitorError {
    object ExecutionError : MonitorError
    object DeviceNotFoundError : MonitorError
}
