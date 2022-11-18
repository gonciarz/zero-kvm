package rg.zerokvm.domain.usecase

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.right
import rg.zerokvm.domain.model.Device
import rg.zerokvm.domain.model.DeviceAssigned
import rg.zerokvm.domain.model.SwitchActiveComputerCommand
import rg.zerokvm.domain.port.api.SwitchActiveComputerError
import rg.zerokvm.domain.port.api.SwitchActiveComputerError.DeviceAssignmentError
import rg.zerokvm.domain.port.api.SwitchActiveComputerError.NotificationError
import rg.zerokvm.domain.port.spi.AssignKeyboard
import rg.zerokvm.domain.port.spi.AssignMonitor
import rg.zerokvm.domain.port.spi.AssignMouse
import rg.zerokvm.domain.port.spi.KeyboardError
import rg.zerokvm.domain.port.spi.PublishDeviceAssignedEvent

suspend fun switchActiveComputer(
    assignLocalMonitor: AssignMonitor,
    assignLocalKeyboard: AssignKeyboard,
    assignLocalMouse: AssignMouse,
    publishDeviceAssignedEvent: PublishDeviceAssignedEvent,
    command: SwitchActiveComputerCommand,
): Either<SwitchActiveComputerError, Unit> {

    // TODO: add current machine config: Computer...

    // Add to cache in progress:

    val monitorSwitch = assignLocalMonitor(command.computer)
        .map { command.toDeviceAssigned(Device.MONITOR) }
        .flatMap { publishDeviceAssignedEvent(it) }

    val keyboardSwitch = assignLocalKeyboard(command.computer)
        .map { command.toDeviceAssigned(Device.KEYBOARD) }
            // TODO: if NotFound then fallback but finish

        .mapLeft {
            when (it) {
                is KeyboardError.ExecutionError -> {}
                is KeyboardError.DeviceNotFoundError -> {}
            }
            DeviceAssignmentError(Device.KEYBOARD) }
        .flatMap {
            publishDeviceAssignedEvent(it)
                .mapLeft { NotificationError(Device.KEYBOARD) }
        }


    val mouseSwitch = assignLocalMouse(command.computer)
        .map { command.toDeviceAssigned(Device.MOUSE) }
        .flatMap { publishDeviceAssignedEvent(it) }

//    return monitorSwitch.zip(keyboardSwitch).zip(monitorSwitch)
//        .mapLeft { }
//        .map { }


// TODO: add timestamp in adapter

    return Unit.right()
}

// TODO: the same type aliases - separate use-cases?
//private fun assignDeviceAndPublishSuccess(assignLocalDevice: (Computer) -> Either<AssignLocalDeviceError, Unit>) {
//
//}
private fun SwitchActiveComputerCommand.toDeviceAssigned(device: Device) = DeviceAssigned(
    refCommandId = commandId,
    computer = computer,
    device = device,
)