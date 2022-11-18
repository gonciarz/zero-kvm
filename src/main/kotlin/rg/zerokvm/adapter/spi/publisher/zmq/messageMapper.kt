package rg.zerokvm.adapter.spi.publisher.zmq

import rg.zerokvm.domain.model.Computer
import rg.zerokvm.domain.model.DeviceAssigned
import rg.zerokvm.infrastructure.AssignedDeviceSuccessEventMessage
import rg.zerokvm.infrastructure.SwitchActiveComputerCommandMessage
import java.time.LocalDateTime
import java.util.*

fun createSwitchActiveComputerCommandMessage(computer: Computer) = SwitchActiveComputerCommandMessage(
    commandId = UUID.randomUUID(),
    createdAt = LocalDateTime.now(),
    computer = computer,
)

fun DeviceAssigned.toMessage() = AssignedDeviceSuccessEventMessage(
    refCommandId = refCommandId,
    eventId = UUID.randomUUID(),
    createdAt = LocalDateTime.now(),
    device = device,
    computer = computer,
)