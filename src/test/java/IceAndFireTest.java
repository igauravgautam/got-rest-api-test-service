import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j
public class IceAndFireTest extends FunctionalTest {
//    https://anapioficeandfire.com/Documentation

    @Test
    public void makeSureThatGoogleIsUp() {
        given().when().get("https://www.google.com").then().statusCode(200);
    }

    @Test
    public void basicPingTest() {
        String response = given().get("https://www.anapioficeandfire.com/api").prettyPrint();
        log.info("response--->>{}", response);
    }

    @Ignore
    @Test
    public void getAllBooksTest() {
        String response = given().get("https://www.anapioficeandfire.com/api/books").prettyPrint();
        log.info("response--->>{}", response);
    }

    @Ignore
    @Test
    public void getFirstBooksTest() {
        String response = given().get("https://www.anapioficeandfire.com/api/books/1").prettyPrint();
        log.info("response--->>{}", response);
    }


    @Test
    public void getFirstCharacterTest() {
        RestAssured.baseURI = "https://www.anapioficeandfire.com:443/api";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get(RestAssured.baseURI + "/characters/2");
//        log.info("Response Body is =>  {}" , response.getBody().prettyPrint());

        int statusCode = response.getStatusCode();

        Assert.assertEquals("Correct Status Code received", "200", String.valueOf(statusCode));

        String contentType = response.header("Content-Type");
        Assert.assertEquals("It matches the expected content Type", "application/json; charset=utf-8", contentType);
        System.out.println("Content type:  " + contentType);

        String headers1 = response.getContentType();
        System.out.println("Content type:  " + headers1);

        Map<String, String> allCookies = response.getCookies();
        System.out.println("Cookies are: " + allCookies);

        ResponseBody body = response.getBody();
//        System.out.println("response body is: " + body.asString());

        Headers allHeaders = response.headers();
        for (Header header : allHeaders) {
            System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
        }
    }

    @Test
    public void invalidParkingSpace() {
        given().when().get("/garage/slots/999")
                .then().statusCode(404);
    }
}