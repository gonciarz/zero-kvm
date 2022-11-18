package rg.zerokvm.adapter.spi.cache

import arrow.core.Either
import arrow.core.right
import kotlinx.coroutines.delay
import rg.zerokvm.domain.model.Computer
import rg.zerokvm.domain.model.DeviceAssignedRecord
import rg.zerokvm.domain.model.DeviceAssignment
import rg.zerokvm.domain.port.spi.CacheError


suspend fun saveDeviceAssignmentToCache(deviceAssignedRecord: DeviceAssignedRecord): Either<CacheError, Unit> {
    delay(100)
    return Either.Left(CacheError.ConnectionError)
}

suspend fun readDevicesAssignmentFromCache(): Either<CacheError, DeviceAssignment> {
    delay(100)
    return DeviceAssignment(
        monitor = Computer.COMPUTER_1,
        keyboard = Computer.COMPUTER_1,
        mouse = Computer.COMPUTER_1,
    ).right()
}