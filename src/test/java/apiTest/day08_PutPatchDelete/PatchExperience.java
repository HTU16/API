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

public class PatchExperience {

    @BeforeClass
    public void beforeClass(){

        baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void patchExperience(){
        String body="{\n" +
                "  \"job\": \" ttttDeveloper\",\n" +
                //  "  \"company\": \"Kraft Techex\",\n" +
                //  "  \"location\": \"USA\",\n" +
                "  \"fromdate\": \"2020-12-12\",\n" +
                "  \"todate\": \"2022-11-11\",\n" +
                "  \"current\": \"false\",\n" +
                "  \"description\": \"Description\"\n" +
                "}";

        Response response=given().accept(ContentType.JSON)
                .pathParam("id",250)
                .queryParam("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMzA2Iiwic3RhcnQiOjE2NzQ4MzkwMzUsImVuZHMiOjE2NzU0NDM4MzV9.zcs-pLn_kzmqbccvLfiZH8JiWWlk7EhqKGwCfQSv4zxMd52_Euhs9Y4CVgYQVREadxuNWtJ3DVVQnXxyWydDjA")
                .body(body)
                .when().log().all()
                .patch("/experience/updatepatch/{id}").prettyPeek();

        Assert.assertEquals(response.statusCode(),200);

    }
}