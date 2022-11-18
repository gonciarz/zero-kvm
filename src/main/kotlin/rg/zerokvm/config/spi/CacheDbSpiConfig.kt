package rg.zerokvm.config.spi

import rg.zerokvm.adapter.spi.cache.readDevicesAssignmentFromCache
import rg.zerokvm.adapter.spi.cache.saveDeviceAssignmentToCache
import rg.zerokvm.config.BeanName
import rg.zerokvm.domain.port.spi.ReadDeviceAssignmentFromCache
import rg.zerokvm.domain.port.spi.SaveDeviceAssignmentToCache
import javax.enterprise.context.ApplicationScoped
import javax.inject.Named

class CacheDbSpiConfig {

    @ApplicationScoped
    @Named(BeanName.SAVE_DEVICE_ASSIGNMENT_TO_CACHE)
    fun saveDeviceAssignmentToCacheBean(): SaveDeviceAssignmentToCache = ::saveDeviceAssignmentToCache

    @ApplicationScoped
    @Named(BeanName.READ_DEVICE_ASSIGNMENT_FROM_CACHE)
    fun readDeviceAssignmentFromCacheBean(): ReadDeviceAssignmentFromCache = ::readDevicesAssignmentFromCache

}