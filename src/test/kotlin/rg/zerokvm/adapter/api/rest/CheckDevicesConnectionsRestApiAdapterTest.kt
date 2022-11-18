package rg.zerokvm.adapter.api.rest

import io.quarkiverse.test.junit.mockk.InjectMock
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test
import rg.zerokvm.adapter.api.rest.util.When
import rg.zerokvm.config.BeanName
import rg.zerokvm.domain.port.api.CheckDevicesConnections
import javax.enterprise.inject.spi.CDI
import javax.inject.Named

@QuarkusTest
internal class CheckDevicesConnectionsRestApiAdapterTest {

    @Named(BeanName.CHECK_DEVICES_CONNECTIONS)
    @InjectMock
    private lateinit var checkDevicesConnections: CheckDevicesConnections

    @Test
    fun `should foo`() {
        RestAssured.given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .When().get("/api/local")
            .then()
            .statusCode(200)
            .body(CoreMatchers.containsString("monitor"))
    }

}