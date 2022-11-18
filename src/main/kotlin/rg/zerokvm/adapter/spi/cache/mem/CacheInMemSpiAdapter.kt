package rg.zerokvm.adapter.spi.cache.mem

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import rg.zerokvm.domain.model.Device
import rg.zerokvm.domain.model.DeviceAssignedRecord
import rg.zerokvm.domain.model.DeviceAssignment
import rg.zerokvm.domain.port.spi.CacheError
import java.util.concurrent.atomic.AtomicReference
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CacheInMemSpiAdapter {

    private val monitor = AtomicReference<DeviceAssignedRecord>()
    private val keyboard = AtomicReference<DeviceAssignedRecord>()
    private val mouse = AtomicReference<DeviceAssignedRecord>()

    suspend fun saveDeviceAssignmentToCache(deviceAssignedRecord: DeviceAssignedRecord): Either<CacheError, Unit> {
        selectReference(deviceAssignedRecord.device)
            .getAndAccumulate(deviceAssignedRecord) { old, new ->
                if (old != null && new.createdAt > old.createdAt) new else old
            }
        return Either.Right(Unit)
    }

    suspend fun readDevicesAssignmentFromCache(): Either<CacheError, DeviceAssignment> {
        val monitorComputer = monitor.acquire?.computer
        val keyboardComputer = keyboard.acquire?.computer
        val mouseComputer = mouse.acquire?.computer
        return if (monitorComputer == null || keyboardComputer == null || mouseComputer == null) {
            CacheError.CacheNotInitialized.left()
        } else {
            DeviceAssignment(monitorComputer, keyboardComputer, mouseComputer).right()
        }
    }

    private fun selectReference(device: Device) = when (device) {
        Device.MONITOR -> monitor
        Device.KEYBOARD -> keyboard
        Device.MOUSE -> mouse
    }

}