package apiTest.day05_HamcrestMatcher;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class HamcretsMatcher {

    @BeforeClass
    public void beforeClass() {

        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

     /*
        given accept type is json
        And path param id is 111
        When user sends a get request to /allusers/getbyid/{id}
        Then status code should be 200
        And content type should be application/json; charset=UTF-8
        And json data has following:
         "id"= 111
         "name"= "Thomas Eduson"
         "job"="Developer"
         */

    @Test
    public void OneUserWithHamcrest() {

        given().accept(ContentType.JSON)      //api nin göndereceği mesaj json tarzında olsun demek.
                .and().pathParam("id", 111)
                .when().get("/allusers/getbyid/{id}")

                .then().statusCode(200)
                .and().assertThat().contentType("application/json; charset=UTF-8")
                .and().body("id[0]", equalTo(111), "name[0]"
                        , equalTo("Thomas Eduson"), "job[0]", equalTo("Developer"));


    }

    /*
         given accept type is json
         And query param pagesize is 50
         And query param page is 1
         When user sends a get request to /allusers/alluser
         Then status code should be 200
         And content type should be application/json; charset=UTF-8
         And response header content-length should be 8653
         And response header server should be Apache/2
         And response headers has Date
         And json data should have "GHAN","aFm","Sebastian"
         And json data should have "QA" for job
         And json data should have "Junior Developer1" for first user's experience job
          */
    @Test
    public void AllUsersWithHamcrest() {

        given().accept(ContentType.JSON)
                .queryParam("pagesize", 50)
                .queryParam("page", 1)
                .when().log().all()
                .get("/allusers/alluser")

                .then().statusCode(200)
                .contentType(equalTo("application/json; charset=UTF-8"))
                .and().assertThat().header("content-length", equalTo("8653"))
                .header("server", equalTo("Apache/2"))
                .headers("Date", notNullValue())
                .body("name", hasItems("GHAN", "aFm", "Sebastian"),
                        "job", hasItems("QA"),
                        "experience[0].job[0]", equalTo("Junior Developer1"),
                        "name[0]", equalTo("MercanS"))
                .log().headers();


    }

}