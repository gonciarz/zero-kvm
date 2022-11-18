package rg.zerokvm.adapter.api.rest

import org.jetbrains.annotations.NotNull
import rg.zerokvm.adapter.api.SwitchActiveComputerDto
import rg.zerokvm.config.BeanName
import rg.zerokvm.domain.port.api.BroadcastSwitchActiveComputer
import javax.inject.Named
import javax.ws.rs.Consumes
import javax.ws.rs.FormParam
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class BroadcastSwitchActiveComputerRestApiAdapter(
    @Named(BeanName.BROADCAST_SWITCH_ACTIVE_COMPUTER)
    private val broadcastSwitchActiveComputer: BroadcastSwitchActiveComputer,
) {

    @Path("/assigment")
    @POST
    suspend fun switchActiveComputer(switchActiveComputer: SwitchActiveComputerDto): Response =
        broadcastSwitchActiveComputer(switchActiveComputer.computer).fold(
            { Response.serverError().entity(it) },
            { Response.accepted() }
        ).build()

}