package rg.zerokvm.config.spi

import rg.zerokvm.adapter.spi.localdevice.shell.KeyboardShellSpiAdapter
import rg.zerokvm.config.BeanName
import rg.zerokvm.domain.port.spi.AssignKeyboard
import rg.zerokvm.domain.port.spi.CheckKeyboardConnection
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces
import javax.inject.Named

class KeyboardShellSpiConfig {

    @ApplicationScoped
    @Named(BeanName.ASSIGN_KEYBOARD)
    fun assignLocalKeyboardBean(keyboardShellSpiAdapter: KeyboardShellSpiAdapter): AssignKeyboard =
        keyboardShellSpiAdapter::assign

    @ApplicationScoped
    @Named(BeanName.CHECK_KEYBOARD_CONNECTION)
    fun queryLocalKeyboardConnectionBean(keyboardShellSpiAdapter: KeyboardShellSpiAdapter): CheckKeyboardConnection =
        keyboardShellSpiAdapter::queryConnection

}