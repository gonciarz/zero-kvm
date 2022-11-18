package rg.zerokvm.config.spi

import rg.zerokvm.adapter.spi.localdevice.shell.MonitorShellSpiAdapter
import rg.zerokvm.config.BeanName
import rg.zerokvm.domain.port.spi.AssignMonitor
import rg.zerokvm.domain.port.spi.CheckMonitorConnection
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces
import javax.inject.Named

class MonitorShellSpiConfig {

    @ApplicationScoped
    @Named(BeanName.ASSIGN_MONITOR)
    fun assignLocalMonitorBean(monitorShellSpiAdapter: MonitorShellSpiAdapter): AssignMonitor =
        monitorShellSpiAdapter::assign

    @ApplicationScoped
    @Named(BeanName.CHECK_MONITOR_CONNECTION)
    fun queryLocalMonitorConnectionBean(monitorShellSpiAdapter: MonitorShellSpiAdapter): CheckMonitorConnection =
        monitorShellSpiAdapter::queryConnection

}