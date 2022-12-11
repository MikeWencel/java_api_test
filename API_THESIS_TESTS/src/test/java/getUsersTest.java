import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;



public class getUsersTest {
    @Test
    public void getUserList(){
        when().get("https://reqres.in/api/users?page=2").then().log().body();

    }

    @Test
    public void getUserListStatusFirstPage(){
        when().get("https://reqres.in/api/users?page=1").then().log().ifValidationFails().statusCode(200);

    }

    @Test
    public void getUserListStatusGetFirstPageNumber(){
        Response response = get("https://reqres.in/api/users?page=1");
        int page_number = response.path("page");
        Assert.assertEquals(page_number, 2);

    }

    @Test
    public void getUserListStatusSecondPage(){
        when().get("https://reqres.in/api/users?page=2").then().log().ifValidationFails().statusCode(200);

    }

    @Test
    public void getUserListCheckUserMail(){
        Response response = get("https://reqres.in/api/users?page=2");
        String email = response.path("data.email[1]");
        Assert.assertEquals(email, "lindsay.ferguson@reqres.in");

    }

    @Test
    public void getUserListCheckid(){
        Response response = get("https://reqres.in/api/users?page=2");
        int id = response.path("data.id[0]");
        Assert.assertEquals(id, 7);

    }
    @Test
    public void getUserListCheckSupport(){
        Response response = get("https://reqres.in/api/users?page=2");
        String web = response.path("support[0]");
        Assert.assertEquals(web, "https://reqres.in/#support-heading");

    }
}
