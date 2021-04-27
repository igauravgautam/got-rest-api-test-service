import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;

import static io.restassured.RestAssured.given;

@Slf4j
public class PostmanTest {

    public static Response postJsonPayload(String payload, String resource) {
        return
                given()
                        .contentType(ContentType.JSON)
                        .body(payload)
//                        .post("/some/resource")
                        .post(resource)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
    }

    @Ignore
    @Test
    public void submitForm() {
        RestAssured.baseURI = "https://www.example.com";
        given().urlEncodingEnabled(true)
                .param("username", "user@site.com")
                .param("password", "Pas54321")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .post("/login")
                .then().statusCode(200);
    }

    @Test
    public void postExample() {
        RestAssured.baseURI = "https://postman-echo.com";
        Response response = given()
                .contentType("application/json")
                .post("https://postman-echo.com/post");
        log.info("response------>>>{}", response);
        String responseAsAString = response.asString();
        log.info("response------>>>{}", response.getBody().jsonPath().getString("headers"));
//        DocumentContext json = JsonPath.parse(responseAsAString);
//        assertEquals(json.read("$.headers.x-forwarded-proto", String.class),"https");
    }
}
