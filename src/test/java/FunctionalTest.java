import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class FunctionalTest {
    //https://www.anapioficeandfire.com/api/characters?page=1&pageSize=10
    @BeforeClass
    public static void setup() {
//        String port = System.getProperty("server.port");
        String port = "443";
        RestAssured.port = Integer.parseInt(port);


//        String basePath = System.getProperty("server.base");
        String basePath = "https://www.anapioficeandfire.com";
        RestAssured.basePath = basePath;

//        String baseHost = System.getProperty("server.host");
        String baseHost = "https://www.anapioficeandfire.com";
        RestAssured.baseURI = baseHost;

    }

}