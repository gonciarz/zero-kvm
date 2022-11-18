package rg.zerokvm.domain.port.api

import arrow.core.Either
import rg.zerokvm.domain.model.DeviceAssignment

typealias QueryDeviceAssignmentCache = suspend () -> Either<QueryDeviceAssignmentCacheError, DeviceAssignment>

sealed interface QueryDeviceAssignmentCacheError {
    object RepositoryConnectionError : QueryDeviceAssignmentCacheError
}
