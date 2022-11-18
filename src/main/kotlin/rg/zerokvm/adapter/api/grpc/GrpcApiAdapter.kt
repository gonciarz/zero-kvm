package rg.zerokvm.adapter.api.grpc

import io.quarkus.grpc.GrpcService
import io.smallrye.mutiny.Uni
import rg.proto.MyService
import rg.proto.VersionRequest
import rg.proto.VersionResponse

@GrpcService
class GrpcApiAdapter : MyService {
    override fun getVersion(request: VersionRequest): Uni<VersionResponse> {
        return Uni.createFrom().item(
            VersionResponse.newBuilder()
                .apply { version = "1.2.3" }
                .build()
        )
    }
}


