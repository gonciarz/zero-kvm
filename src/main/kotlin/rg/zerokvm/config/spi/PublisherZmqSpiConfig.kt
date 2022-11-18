package rg.zerokvm.config.spi

import arrow.core.partially1
import rg.zerokvm.adapter.spi.publisher.zmq.MessageSender
import rg.zerokvm.adapter.spi.publisher.zmq.publishDeviceAssignedSuccessEvent
import rg.zerokvm.adapter.spi.publisher.zmq.publishSwitchActiveComputerCommand
import rg.zerokvm.config.BeanName
import rg.zerokvm.domain.port.spi.PublishDeviceAssignedEvent
import rg.zerokvm.domain.port.spi.PublishSwitchActiveComputer
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces
import javax.inject.Named

class PublisherZmqSpiConfig {

    @ApplicationScoped
    @Named(BeanName.PUBLISH_SWITCH_ACTIVE_COMPUTER)
    fun publishSwitchActiveComputerBean(messageSender: MessageSender): PublishSwitchActiveComputer =
        ::publishSwitchActiveComputerCommand
            .partially1(messageSender)

    @ApplicationScoped
    @Named(BeanName.PUBLISH_DEVICE_ASSIGNED_EVENT)
    fun publishDeviceAssignedEventBean(messageSender: MessageSender): PublishDeviceAssignedEvent =
        ::publishDeviceAssignedSuccessEvent
            .partially1(messageSender)

}