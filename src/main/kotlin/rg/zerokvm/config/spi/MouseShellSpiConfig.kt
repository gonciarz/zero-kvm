package rg.zerokvm.config.spi

import rg.zerokvm.adapter.spi.localdevice.shell.MouseShellSpiAdapter
import rg.zerokvm.config.BeanName
import rg.zerokvm.domain.port.spi.AssignMouse
import rg.zerokvm.domain.port.spi.CheckMouseConnection
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces
import javax.inject.Named

class MouseShellSpiConfig {

    @ApplicationScoped
    @Named(BeanName.ASSIGN_MOUSE)
    fun assignLocalMouseBean(mouseShellSpiAdapter: MouseShellSpiAdapter): AssignMouse =
        mouseShellSpiAdapter::assign

    @ApplicationScoped
    @Named(BeanName.CHECK_MOUSE_CONNECTION)
    fun queryLocalMouseConnectionBean(mouseShellSpiAdapter: MouseShellSpiAdapter): CheckMouseConnection =
        mouseShellSpiAdapter::queryConnection

}