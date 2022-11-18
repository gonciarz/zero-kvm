package rg.zerokvm.infrastructure

import rg.zerokvm.domain.model.Computer
import rg.zerokvm.domain.model.Device
import java.time.LocalDateTime
import java.util.*

sealed interface Message

data class SwitchActiveComputerCommandMessage(
    val commandId: UUID,
    val createdAt: LocalDateTime,
    val computer: Computer,
) : Message

data class AssignedDeviceSuccessEventMessage(
    val refCommandId: UUID,
    val eventId: UUID,
    val createdAt: LocalDateTime,
    val device: Device,
    val computer: Computer,
) : Message

