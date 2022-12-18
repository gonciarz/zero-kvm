package rg.zerokvm.adapter.api.rest

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test
import rg.zerokvm.adapter.api.rest.util.When

@QuarkusTest
internal class QueryDeviceAssignmentRestApiAdapterTest {

    @Test
    fun `should query device assignment`() {
        RestAssured.given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .When().get("/api/assignment")
            .then()
            .statusCode(200)
            .body(CoreMatchers.containsString("monitor"))
    }

}