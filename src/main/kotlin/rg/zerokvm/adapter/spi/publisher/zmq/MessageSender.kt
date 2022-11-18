package rg.zerokvm.adapter.spi.publisher.zmq

import arrow.core.Either
import kotlinx.coroutines.delay
import mu.KotlinLogging
import rg.zerokvm.domain.port.spi.PublisherError
import rg.zerokvm.infrastructure.Message
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class MessageSender {

    private val logger = KotlinLogging.logger {}
    suspend fun send(queue: String, message: Message): Either<PublisherError, Unit> {
        logger.info("Sending message: $message to queue: $queue")
        delay(100)
        return Either.Right(Unit)
    }
}