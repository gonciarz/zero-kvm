package rg.zerokvm.domain.port.api

import arrow.core.Either
import rg.zerokvm.domain.model.DeviceAssignedRecord
import rg.zerokvm.domain.model.DeviceAssignment

typealias UpdateDeviceAssignmentCache = suspend (DeviceAssignedRecord) -> Either<UpdateDeviceAssignmentCacheError, Unit>

sealed interface UpdateDeviceAssignmentCacheError {
    object UpdateCacheError : UpdateDeviceAssignmentCacheError
}
