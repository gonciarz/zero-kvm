package rg.zerokvm.adapter.spi.localdevice.shell

import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.FreeSpec
import io.mockk.clearAllMocks
import rg.zerokvm.domain.model.Computer

class KeyboardShellSpiAdapterTest : FreeSpec({

    val keyboardShellSpiAdapter = KeyboardShellSpiAdapter()

    beforeContainer {
        clearAllMocks()
    }

    "positive scenario".config(enabled = false) {
        // given
//        coEvery { saveDeviceAssignmentToCache.invoke(any()) } returns Unit.right()

        // when
        val result = keyboardShellSpiAdapter.assign(Computer.COMPUTER_1)

        // then
        result shouldBeRight (Unit)
//        coVerify(exactly = 1) { saveDeviceAssignmentToCache.invoke(any()) }
    }

    "negative scenario" - {

        "should return update cache error when saving to cache fails" {
            // given
//            coEvery { saveDeviceAssignmentToCache.invoke(any()) } returns CacheError.WriteError.left()

            // when
            val result = keyboardShellSpiAdapter.assign(Computer.COMPUTER_1)

            // then
//            result shouldBeLeft (UpdateDeviceAssignmentCacheError.UpdateCacheError)
//            coVerify(exactly = 1) { saveDeviceAssignmentToCache.invoke(any()) }
        }
    }

})
