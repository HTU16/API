package apiTest.day07_POST_Request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class PostRequestDemo {
    @BeforeClass
    public void beforeClass(){

        baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void postNewUser(){

        String jsonBody="{\n" +
                "  \"name\": \"Sare1\",\n" +
                "  \"email\": \"sare1@krafttechexlab.com\",\n" +
                "  \"password\": \"sare1\",\n" +
                "  \"about\": \"from Aydin\",\n" +
                "  \"terms\": \"2\"\n" +
                "}";

        Response response= given().accept(ContentType.JSON) // hey api send me body as json format
                .and()
                .contentType(ContentType.JSON)//hey api I am sending json body
                .and()
                .body(jsonBody)
                .when()
                .post("/allusers/register");
        assertEquals(response.statusCode(),404);
        response.prettyPrint();

        assertFalse(response.body().asString().contains("token"));

    }

    @Test
    public void postNewUser2(){

        Map<String,Object> requestMap=new HashMap<>();

        requestMap.put("name","sare2");
        requestMap.put("email","sare2@krafttechexlab.com");
        requestMap.put("password","sare2");
        requestMap.put("about","About Me");
        requestMap.put("terms","3");

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap) //serialization
                .when()
                .post("/allusers/register");

        assertEquals(response.statusCode(),404);
        response.prettyPrint();

        assertFalse(response.body().asString().contains("token"));

    }

    @Test
    public void postNewUser3(){

        NewUserInfo newUserInfo=new NewUserInfo();

        newUserInfo.setName("sare3");
        newUserInfo.setEmail("sare3@krafttechexlab.com");
        newUserInfo.setPassword("sare3");
        newUserInfo.setAbout("About Me");
        newUserInfo.setTerms("4");

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(newUserInfo) //serialization
                .when()
                .post("/allusers/register");

        assertEquals(response.statusCode(),404);
        response.prettyPrint();

        assertFalse(response.body().asString().contains("token"));

    }

    @Test
    public void postNewUser4(){
        NewUserInfo newUserInfo=new NewUserInfo("Sare4",
                "sare4@krafttechexlab.com","sare4","About Me","5");

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(newUserInfo) //serialization
                .when()
                .post("/allusers/register");

        assertEquals(response.statusCode(),404);
        response.prettyPrint();

        assertFalse(response.body().asString().contains("token"));


    }



}