package rg.zerokvm.domain.port.spi

import arrow.core.Either
import rg.zerokvm.domain.model.DeviceAssigned
import rg.zerokvm.domain.model.Computer

typealias PublishSwitchActiveComputer = suspend (Computer) -> Either<PublisherError, Unit>
typealias PublishDeviceAssignedEvent = suspend (DeviceAssigned) -> Either<PublisherError, Unit>

sealed interface PublisherError {
    object ConnectionError : PublisherError
}
