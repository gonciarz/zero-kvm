package rg.zerokvm.adapter.api.rest

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.junit.jupiter.api.Test
import rg.zerokvm.adapter.api.SwitchActiveComputerDto
import rg.zerokvm.adapter.api.rest.util.When
import rg.zerokvm.domain.model.Computer

@QuarkusTest
internal class BroadcastSwitchActiveComputerRestApiAdapterTest {

    @Test
    fun `should broadcast switch active computer`() {
        RestAssured.given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(SwitchActiveComputerDto(Computer.COMPUTER_1))
            .When().post("/api/assigment")
            .then()
            .statusCode(202)
    }

}
