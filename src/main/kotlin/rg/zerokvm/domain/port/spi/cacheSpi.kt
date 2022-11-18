package rg.zerokvm.domain.port.spi

import arrow.core.Either
import rg.zerokvm.domain.model.DeviceAssignedRecord
import rg.zerokvm.domain.model.DeviceAssignment

typealias SaveDeviceAssignmentToCache = suspend (DeviceAssignedRecord) -> Either<CacheError, Unit>
// TODO: change output: add status? (in progress)...
typealias ReadDeviceAssignmentFromCache = suspend () -> Either<CacheError, DeviceAssignment>

sealed interface CacheError {
    object ConnectionError : CacheError
    object ReadError : CacheError
    object WriteError : CacheError
    object CacheNotInitialized : CacheError
}
