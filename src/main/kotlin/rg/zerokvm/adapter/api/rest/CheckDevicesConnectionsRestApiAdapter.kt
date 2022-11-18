package rg.zerokvm.adapter.api.rest

import rg.zerokvm.adapter.api.toDto
import rg.zerokvm.config.BeanName
import rg.zerokvm.domain.port.api.CheckDevicesConnections
import javax.inject.Named
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class CheckDevicesConnectionsRestApiAdapter(
    @Named(BeanName.CHECK_DEVICES_CONNECTIONS)
    private val checkDevicesConnections: CheckDevicesConnections,
) {

    @Path("/local")
    @GET
    suspend fun localDevices(): Response =
        checkDevicesConnections().fold(
            { Response.serverError().entity(it) },
            { Response.ok(it.toDto()) },
        ).build()

}