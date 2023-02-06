package apiTest.day07_POST_Request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.hamcrest.Matchers.*;

public class PostEducation {

    @BeforeClass
    public void beforeClass() {

        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void postNewUser() {
        NewUserInfo newUserInfo = new NewUserInfo("Sare6",
                "sare6@krafttechexlab.com", "sare6", "About Me", "5");

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(newUserInfo) //serialization
                .when()
                .post("/allusers/register");

        assertEquals(response.statusCode(), 200);
        response.prettyPrint();

        String token = response.path("token");

        String educationBody = "{\n" +
                "  \"school\": \"Kraftech\",\n" +
                "  \"degree\": \"BootCamp\",\n" +
                "  \"study\": \"SDET\",\n" +
                "  \"fromdate\": \"2020-01-01\",\n" +
                "  \"todate\": \"2020-08-01\",\n" +
                "  \"current\": \"false\",\n" +
                "  \"description\": \"SDET Bootcamp\"\n" +
                "}";

        response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(educationBody)
                .and()
                .queryParam("token", token)
                .when()
                .post("/education/add");

        assertEquals(response.statusCode(), 200);
        response.prettyPrint();

    }

    @Test
    public void postNewUserAndVerify() {
        String name="Sare11";
        String email="sare11@krafttechexlab.com";
        String password="sare11";
        String about="About Me";
        String terms="7";


        Map<String,Object> requestMap=new HashMap<>();

        requestMap.put("name",name);
        requestMap.put("email",email);
        requestMap.put("password",password);
        requestMap.put("about",about);
        requestMap.put("terms",terms);

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap) //serialization
                .when()
                .post("/allusers/register");

        assertEquals(response.statusCode(),200);

        String token = response.path("token");
        //String userID=response.path("id");

        Map<String,Object> educationBody=new HashMap<>();
        educationBody.put("school","Kraftech");
        educationBody.put("degree","BootCamp");
        educationBody.put("study","SDET");
        educationBody.put("fromdate","2020-01-01");
        educationBody.put("todate","2020-08-01");
        educationBody.put("current","false");
        educationBody.put("description","Description");

        response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(educationBody)
                .and()
                .queryParam("token", token)
                .when()
                .post("/education/add");
        response.prettyPrint();
        assertEquals(response.statusCode(),200);

        //verify body

        int id=response.path("id");

        response=given().accept(ContentType.JSON)
                .and()
                .queryParam("token",token)
                .and()
                .pathParam("id",id)
                .when()
                .get("/education/getbyid/{id}");

        response.prettyPrint();
        assertEquals(response.statusCode(),200);

        //verify with path
        //System.out.println("userID = " + userID);
        System.out.println("education id = " + id);
        // assertEquals(response.path("userid"),userID);
        assertEquals(response.path("school"),"Kraftech");

        //verify using hamcrest matcher

        given().accept(ContentType.JSON)
                .and()
                .queryParam("token",token)
                .and()
                .pathParam("id",id)
                .when()
                .get("/education/getbyid/{id}")
                .then()
                .assertThat()
                .body("school",equalTo("Kraftech"),
                        //"userid",equalTo(userID),
                        "study",equalTo("SDET")).log().all();



    }


}