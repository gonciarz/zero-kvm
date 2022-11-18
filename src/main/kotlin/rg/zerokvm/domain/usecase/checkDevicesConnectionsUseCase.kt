package rg.zerokvm.domain.usecase

import arrow.core.Either
import arrow.core.right
import rg.zerokvm.domain.model.ConnectionStatus
import rg.zerokvm.domain.model.LocalDevices
import rg.zerokvm.domain.port.api.CheckDevicesConnectionsError
import rg.zerokvm.domain.port.spi.CheckKeyboardConnection
import rg.zerokvm.domain.port.spi.CheckMonitorConnection
import rg.zerokvm.domain.port.spi.CheckMouseConnection

suspend fun checkDevicesConnections(
    checkMonitorConnection: CheckMonitorConnection,
    checkKeyboardConnection: CheckKeyboardConnection,
    checkMouseConnection: CheckMouseConnection,
): Either<CheckDevicesConnectionsError, LocalDevices> {

    val monitor = checkMonitorConnection()
    val keyboard = checkKeyboardConnection()
    val mouse = checkMouseConnection()

    return LocalDevices(
        monitor = ConnectionStatus.CONNECTED,
        keyboard = ConnectionStatus.CONNECTED,
        mouse = ConnectionStatus.CONNECTED,
    ).right()
}