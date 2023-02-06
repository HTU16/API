package apiTest.day08_PutPatchDelete;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
public class PostNewExperince {

    @BeforeClass
    public void beforeClass(){

        baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }
    @Test
    public void newExperience(){
        String body="{\n" +
                "  \"job\": \"SDET\",\n" +
                "  \"company\": \"GHAN Techex\",\n" +
                "  \"location\": \"NL\",\n" +
                "  \"fromdate\": \"2023-01-12\",\n" +
                "  \"todate\": \"2016-12-11\",\n" +
                "  \"current\": \"true\",\n" +
                "  \"description\": \"Nice job\"\n" +
                "}";

        Response response=given().accept(ContentType.JSON)
                .queryParam("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMzA2Iiwic3RhcnQiOjE2NzQ4MzkwMzUsImVuZHMiOjE2NzU0NDM4MzV9.zcs-pLn_kzmqbccvLfiZH8JiWWlk7EhqKGwCfQSv4zxMd52_Euhs9Y4CVgYQVREadxuNWtJ3DVVQnXxyWydDjA")
                .body(body)
                .when().log().all()
                .post("/experience/add").prettyPeek();


//227 id1
//237 id2
//250 id3

    }
}