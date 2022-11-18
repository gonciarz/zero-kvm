package rg.zerokvm.config.api

import arrow.core.partially1
import arrow.core.right
import io.quarkus.arc.Arc
import rg.zerokvm.config.BeanName
import rg.zerokvm.domain.port.api.BroadcastSwitchActiveComputer
import rg.zerokvm.domain.port.api.CheckDevicesConnections
import rg.zerokvm.domain.port.api.QueryDeviceAssignmentCache
import rg.zerokvm.domain.port.spi.CheckKeyboardConnection
import rg.zerokvm.domain.port.spi.CheckMonitorConnection
import rg.zerokvm.domain.port.spi.CheckMouseConnection
import rg.zerokvm.domain.port.spi.PublishSwitchActiveComputer
import rg.zerokvm.domain.port.spi.ReadDeviceAssignmentFromCache
import rg.zerokvm.domain.usecase.broadcastSwitchActiveComputer
import rg.zerokvm.domain.usecase.checkDevicesConnections
import rg.zerokvm.domain.usecase.queryCacheForDevicesAssignment
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Typed
import javax.enterprise.util.TypeLiteral
import javax.inject.Named

class GrpcApiConfig {

    init {
        val broadcastSwitchActiveComputer: BroadcastSwitchActiveComputer = { _ -> Unit.right() }

        val typeLiteral = object : TypeLiteral<BroadcastSwitchActiveComputer>() {}
//        Arc.container().instance(typeLiteral)

    }

    @ApplicationScoped
    @Named(BeanName.BROADCAST_SWITCH_ACTIVE_COMPUTER)
    fun broadcastSwitchActiveComputerBean(
        @Named(BeanName.PUBLISH_SWITCH_ACTIVE_COMPUTER) publishSwitchActiveComputer: PublishSwitchActiveComputer,
    ): BroadcastSwitchActiveComputer = ::broadcastSwitchActiveComputer
        .partially1(publishSwitchActiveComputer)

    @ApplicationScoped
    @Named(BeanName.CHECK_DEVICES_CONNECTIONS)
    fun checkDevicesConnectionsBean(
        @Named(BeanName.CHECK_MONITOR_CONNECTION) checkMonitorConnection: CheckMonitorConnection,
        @Named(BeanName.CHECK_KEYBOARD_CONNECTION) checkKeyboardConnection: CheckKeyboardConnection,
        @Named(BeanName.CHECK_MOUSE_CONNECTION) checkMouseConnection: CheckMouseConnection,
    ): CheckDevicesConnections = ::checkDevicesConnections
        .partially1(checkMonitorConnection)
        .partially1(checkKeyboardConnection)
        .partially1(checkMouseConnection)

    @ApplicationScoped
    @Named(BeanName.QUERY_DEVICE_ASSIGNMENT_CACHE)
    fun queryDeviceAssignmentCacheBean(
        @Named(BeanName.READ_DEVICE_ASSIGNMENT_FROM_CACHE) readDevicesAssignmentFromCache: ReadDeviceAssignmentFromCache,
    ): QueryDeviceAssignmentCache = ::queryCacheForDevicesAssignment
        .partially1(readDevicesAssignmentFromCache)

}