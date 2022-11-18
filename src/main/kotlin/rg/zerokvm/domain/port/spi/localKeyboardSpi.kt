package rg.zerokvm.domain.port.spi

import arrow.core.Either
import rg.zerokvm.domain.model.Computer
import rg.zerokvm.domain.model.ConnectionStatus

typealias AssignKeyboard = suspend (Computer) -> Either<KeyboardError, Unit>
typealias CheckKeyboardConnection = suspend () -> Either<KeyboardError, ConnectionStatus>

sealed interface KeyboardError {
    object ExecutionError : KeyboardError
    object DeviceNotFoundError : KeyboardError
}
