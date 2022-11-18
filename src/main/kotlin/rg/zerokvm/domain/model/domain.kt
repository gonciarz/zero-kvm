package rg.zerokvm.domain.model

import java.time.LocalDateTime
import java.util.*

enum class Device {
    MONITOR,
    KEYBOARD,
    MOUSE,
    ;
}

data class DeviceAssignment(
    val monitor: Computer,
    val keyboard: Computer,
    val mouse: Computer,
)


enum class ConnectionStatus {
    CONNECTED,
    NOT_FOUND,
}

data class LocalDevices(
    val monitor: ConnectionStatus,
    val keyboard: ConnectionStatus,
    val mouse: ConnectionStatus,
)

// TODO: to remove?
sealed interface Machine {
    val id: Int
}

enum class Computer(override val id: Int) : Machine {
    COMPUTER_1(1),
    COMPUTER_2(2),
    COMPUTER_3(3)
}

// TODO: to remove?
object Uninitialized : Machine {
    override val id: Int = -1
}

// TODO: to remove?
data class AssignDevice(
    val commandId: UUID,
    val device: Device,
    val computer: Computer,
)

data class SwitchActiveComputerCommand(
    val commandId: UUID,
    val createdAt: LocalDateTime,
    val computer: Computer,
)

// TODO: ???
enum class DeviceAssignmentStatus {
    IN_PROGRESS,
    COMPLETED,
}

data class DeviceAssigned(
    val refCommandId: UUID,
    val device: Device,
    val computer: Computer,
)

data class DeviceAssignedRecord(
    val device: Device,
    val computer: Computer,
    val createdAt: LocalDateTime,
//    val status: DeviceAssignmentStatus,
)


// TODO: to remove?
data class SaveDeviceAssigment(
    val computer: Computer,
    val device: Device,
    val timestamp: LocalDateTime,
    val refCommandId: UUID,
)

