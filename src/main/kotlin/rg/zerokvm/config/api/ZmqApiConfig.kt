package rg.zerokvm.config.api

import arrow.core.partially1
import rg.zerokvm.config.BeanName
import rg.zerokvm.domain.port.api.SwitchActiveComputer
import rg.zerokvm.domain.port.api.UpdateDeviceAssignmentCache
import rg.zerokvm.domain.port.spi.AssignKeyboard
import rg.zerokvm.domain.port.spi.AssignMonitor
import rg.zerokvm.domain.port.spi.AssignMouse
import rg.zerokvm.domain.port.spi.PublishDeviceAssignedEvent
import rg.zerokvm.domain.port.spi.SaveDeviceAssignmentToCache
import rg.zerokvm.domain.usecase.switchActiveComputer
import rg.zerokvm.domain.usecase.updateAssignmentCache
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces
import javax.inject.Named

class ZmqApiConfig {

    @ApplicationScoped
    @Named(BeanName.SWITCH_ACTIVE_COMPUTER)
    fun switchActiveComputerBean(
        @Named(BeanName.ASSIGN_MONITOR) assignMonitor: AssignMonitor,
        @Named(BeanName.ASSIGN_KEYBOARD) assignKeyboard: AssignKeyboard,
        @Named(BeanName.ASSIGN_MOUSE) assignMouse: AssignMouse,
        @Named(BeanName.PUBLISH_DEVICE_ASSIGNED_EVENT) publishDeviceAssignedEvent: PublishDeviceAssignedEvent,
    ): SwitchActiveComputer = ::switchActiveComputer
        .partially1(assignMonitor)
        .partially1(assignKeyboard)
        .partially1(assignMouse)
        .partially1(publishDeviceAssignedEvent)

    @ApplicationScoped
    @Named(BeanName.UPDATE_DEVICE_ASSIGNMENT_CACHE)
    fun updateDeviceAssignmentCacheBean(
        @Named(BeanName.SAVE_DEVICE_ASSIGNMENT_TO_CACHE) saveDeviceAssignmentToCache: SaveDeviceAssignmentToCache,
    ): UpdateDeviceAssignmentCache = ::updateAssignmentCache
        .partially1(saveDeviceAssignmentToCache)


}