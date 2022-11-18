package rg.zerokvm.domain.usecase

import arrow.core.Either
import rg.zerokvm.domain.model.DeviceAssignment
import rg.zerokvm.domain.port.api.QueryDeviceAssignmentCacheError
import rg.zerokvm.domain.port.spi.CacheError
import rg.zerokvm.domain.port.spi.ReadDeviceAssignmentFromCache

suspend fun queryCacheForDevicesAssignment(
    readDevicesAssignmentFromCache: ReadDeviceAssignmentFromCache,
): Either<QueryDeviceAssignmentCacheError, DeviceAssignment> {

    return readDevicesAssignmentFromCache()
        .mapLeft {
            when (it) {
                is CacheError.ConnectionError -> {}
                is CacheError.ReadError -> {}
                is CacheError.WriteError -> {}
                is CacheError.CacheNotInitialized -> {}
            }
            QueryDeviceAssignmentCacheError.RepositoryConnectionError
        }
}
