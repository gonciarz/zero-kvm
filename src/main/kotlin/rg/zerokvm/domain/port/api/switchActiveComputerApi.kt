package rg.zerokvm.domain.port.api

import arrow.core.Either
import rg.zerokvm.domain.model.Device
import rg.zerokvm.domain.model.SwitchActiveComputerCommand

typealias SwitchActiveComputer = suspend (SwitchActiveComputerCommand) -> Either<SwitchActiveComputerError, Unit>

sealed interface SwitchActiveComputerError {

    val device: Device

    data class DeviceAssignmentError(override val device: Device) : SwitchActiveComputerError
    data class NotificationError(override val device: Device) : SwitchActiveComputerError
}
