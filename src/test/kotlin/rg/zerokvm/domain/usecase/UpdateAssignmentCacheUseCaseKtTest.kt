package rg.zerokvm.domain.usecase

import arrow.core.left
import arrow.core.partially1
import arrow.core.right
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.FreeSpec
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import rg.zerokvm.domain.model.Computer.COMPUTER_1
import rg.zerokvm.domain.model.Device.MOUSE
import rg.zerokvm.domain.model.DeviceAssignedRecord
import rg.zerokvm.domain.port.api.UpdateDeviceAssignmentCache
import rg.zerokvm.domain.port.api.UpdateDeviceAssignmentCacheError
import rg.zerokvm.domain.port.spi.CacheError
import rg.zerokvm.domain.port.spi.SaveDeviceAssignmentToCache
import java.time.LocalDateTime

class UpdateAssignmentCacheUseCaseKtTest : FreeSpec({

    val saveDeviceAssignmentToCache: SaveDeviceAssignmentToCache = mockk()
    val updateDeviceAssignmentCache: UpdateDeviceAssignmentCache = ::updateAssignmentCache
        .partially1(saveDeviceAssignmentToCache)

    val deviceAssignedRecord = DeviceAssignedRecord(MOUSE, COMPUTER_1, LocalDateTime.now())

    beforeContainer {
        clearAllMocks()
    }

    "positive scenario" {
        // given
        coEvery { saveDeviceAssignmentToCache.invoke(any()) } returns Unit.right()

        // when
        val result = updateDeviceAssignmentCache.invoke(deviceAssignedRecord)

        // then
        result shouldBeRight (Unit)
        coVerify(exactly = 1) { saveDeviceAssignmentToCache.invoke(any()) }
    }

    "negative scenario" - {

        "should return update cache error when saving to cache fails" {
            // given
            coEvery { saveDeviceAssignmentToCache.invoke(any()) } returns CacheError.WriteError.left()

            // when
            val result = updateDeviceAssignmentCache.invoke(deviceAssignedRecord)

            // then
            result shouldBeLeft (UpdateDeviceAssignmentCacheError.UpdateCacheError)
            coVerify(exactly = 1) { saveDeviceAssignmentToCache.invoke(any()) }
        }
    }

})
