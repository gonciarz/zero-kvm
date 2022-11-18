package rg.zerokvm.domain.port.spi

import arrow.core.Either
import rg.zerokvm.domain.model.Computer
import rg.zerokvm.domain.model.ConnectionStatus

typealias AssignMouse = suspend (Computer) -> Either<MouseError, Unit>
typealias CheckMouseConnection = suspend () -> Either<MouseError, ConnectionStatus>

sealed interface MouseError {
    object ExecutionError : MouseError
    object DeviceNotFoundError : MouseError
}
