package rg.zerokvm.adapter.api

import rg.zerokvm.domain.model.Computer
import rg.zerokvm.domain.model.ConnectionStatus
import rg.zerokvm.domain.model.DeviceAssignment
import rg.zerokvm.domain.model.LocalDevices

data class LocalDevicesDto(
    val monitor: ConnectionStatus,
    val keyboard: ConnectionStatus,
    val mouse: ConnectionStatus,
)

data class DeviceAssignmentDto(
    val monitor: Computer,
    val keyboard: Computer,
    val mouse: Computer,
)

data class SwitchActiveComputerDto(
    val computer: Computer,
)

fun LocalDevices.toDto() = LocalDevicesDto(
    monitor = monitor,
    keyboard = keyboard,
    mouse = mouse,
)

fun DeviceAssignment.toDto() = DeviceAssignmentDto(
    monitor = monitor,
    keyboard = keyboard,
    mouse = mouse,
)
