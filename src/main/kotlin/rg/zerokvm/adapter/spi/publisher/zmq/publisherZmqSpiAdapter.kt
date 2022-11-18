package rg.zerokvm.adapter.spi.publisher.zmq

import arrow.core.Either
import mu.KotlinLogging
import rg.zerokvm.domain.model.Computer
import rg.zerokvm.domain.model.DeviceAssigned
import rg.zerokvm.domain.port.spi.PublisherError
import rg.zerokvm.infrastructure.Queue

private val logger = KotlinLogging.logger {}
suspend fun publishSwitchActiveComputerCommand(
    messageSender: MessageSender,
    computer: Computer,
): Either<PublisherError, Unit> =
    createSwitchActiveComputerCommandMessage(computer)
        .let { messageSender.send(Queue.ASSIGN_DEVICE_COMMANDS, it) }

suspend fun publishDeviceAssignedSuccessEvent(
    messageSender: MessageSender,
    assignedDeviceSuccess: DeviceAssigned,
): Either<PublisherError, Unit> =
    messageSender.send(Queue.DEVICE_ASSIGNED_SUCCESS_EVENTS, assignedDeviceSuccess.toMessage())


