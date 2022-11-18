package rg.zerokvm.domain.usecase

import arrow.core.Either
import rg.zerokvm.domain.model.DeviceAssignedRecord
import rg.zerokvm.domain.port.api.UpdateDeviceAssignmentCacheError
import rg.zerokvm.domain.port.spi.SaveDeviceAssignmentToCache

suspend fun updateAssignmentCache(
    saveDeviceAssignmentToCache: SaveDeviceAssignmentToCache,
    deviceAssignedRecord: DeviceAssignedRecord,
): Either<UpdateDeviceAssignmentCacheError, Unit> {
    return saveDeviceAssignmentToCache(deviceAssignedRecord)
        .mapLeft { UpdateDeviceAssignmentCacheError.UpdateCacheError }
}
