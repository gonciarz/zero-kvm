package rg.zerokvm.adapter.api.rest

import rg.zerokvm.adapter.api.toDto
import rg.zerokvm.config.BeanName
import rg.zerokvm.domain.port.api.QueryDeviceAssignmentCache
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
class QueryDeviceAssignmentRestApiAdapter(
    @Named(BeanName.QUERY_DEVICE_ASSIGNMENT_CACHE)
    private val port: QueryDeviceAssignmentCache,
) {

    @Path(PATH_QUERY_DEVICE_ASSIGNMENT_CACHE)
    @GET
    suspend fun queryDeviceAssignmentCache(): Response =
        port().fold(
            { Response.serverError().entity(it) },
            { Response.ok(it.toDto()) },
        ).build()

    companion object {
        const val PATH_QUERY_DEVICE_ASSIGNMENT_CACHE = "/assigment"
    }

}