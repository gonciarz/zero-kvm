package rg.zerokvm.domain.port.api

import arrow.core.Either
import rg.zerokvm.domain.model.Device
import rg.zerokvm.domain.model.LocalDevices

typealias CheckDevicesConnections = suspend () -> Either<CheckDevicesConnectionsError, LocalDevices>

sealed interface CheckDevicesConnectionsError {
    data class ExecutionError(val device: Device) : CheckDevicesConnectionsError
}
