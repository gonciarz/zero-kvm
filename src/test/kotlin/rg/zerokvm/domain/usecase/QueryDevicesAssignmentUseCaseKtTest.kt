package rg.zerokvm.domain.usecase

import arrow.core.left
import arrow.core.partially1
import arrow.core.right
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.FreeSpec
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import rg.zerokvm.domain.model.Computer
import rg.zerokvm.domain.model.DeviceAssignment
import rg.zerokvm.domain.port.api.QueryDeviceAssignmentCache
import rg.zerokvm.domain.port.api.QueryDeviceAssignmentCacheError
import rg.zerokvm.domain.port.spi.CacheError
import rg.zerokvm.domain.port.spi.ReadDeviceAssignmentFromCache

class QueryDevicesAssignmentUseCaseKtTest : FreeSpec({

    val readDevicesAssignmentFromCache: ReadDeviceAssignmentFromCache = mockk()
    val queryCacheForDevicesAssignment: QueryDeviceAssignmentCache = ::queryCacheForDevicesAssignment
        .partially1(readDevicesAssignmentFromCache)

    beforeContainer {
        clearAllMocks()
    }

    "positive scenario" {
        // given
        val deviceAssignment = DeviceAssignment(
            monitor = Computer.COMPUTER_1,
            keyboard = Computer.COMPUTER_1,
            mouse = Computer.COMPUTER_2,
        )
        coEvery { queryCacheForDevicesAssignment.invoke() } returns deviceAssignment.right()

        // when
        val result = queryCacheForDevicesAssignment()

        // then
        result.shouldBeRight(deviceAssignment)
    }

    "negative scenario" - {

        "should return query error when cannot read cache" {
            // given
            coEvery { readDevicesAssignmentFromCache.invoke() } returns CacheError.ReadError.left()

            // when
            val result = queryCacheForDevicesAssignment()

            // then
            result.shouldBeLeft(QueryDeviceAssignmentCacheError.RepositoryConnectionError)
        }
    }

})
