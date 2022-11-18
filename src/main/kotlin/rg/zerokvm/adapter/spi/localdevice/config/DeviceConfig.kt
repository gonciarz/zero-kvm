package rg.zerokvm.adapter.spi.localdevice.config

import io.quarkus.runtime.annotations.StaticInitSafe
import io.smallrye.config.ConfigMapping

@StaticInitSafe
@ConfigMapping(prefix = "device")
interface DeviceConfig {

    fun monitor(): MonitorSpec
    fun keyboard(): DeviceSpec
    fun mouse(): DeviceSpec

    interface MonitorSpec : DeviceSpec {
        fun channels(): List<MonitorSourceSpec>

        interface MonitorSourceSpec {
            fun name(): String
            fun code(): String
        }
    }

    interface DeviceSpec {
        fun model(): String
        fun cmdSet(): String
        fun cmdGet(): String
    }
}
