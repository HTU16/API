package apiTest.day05_HamcrestMatcher;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class JsonToJavaCollection {

    @BeforeClass
    public void beforeClass(){

        baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }
    @Test
    public void UserToMap(){

        Response response=given().accept(ContentType.JSON)
                .when().get("https://demoqa.com/Account/v1/User/11");

        Assert.assertEquals(response.statusCode(),401);

        Map<String,Object>jsonMap= response.body().as(Map.class);
        System.out.println("jsonMap = " + jsonMap);

        //verify the message
        String message= (String) jsonMap.get("message");
        System.out.println("message = " + message);
        Assert.assertEquals(message,"User not authorized!");

        //verify the code
        String code= (String) jsonMap.get("code");
        System.out.println("code = " + code);
        Assert.assertEquals(code,"1200");

    }

    @Test
    public void AllUsersToMap(){

        Response response=given().accept(ContentType.JSON)
                .queryParam("pagesize",50)
                .queryParam("page",1)
                .when().get("/allusers/alluser");

        Assert.assertEquals(response.statusCode(),200);

        //we need to de-serialiaze Json response to java collection
        //birden fazla json body bulundurdugundan list  of map yapmamiz gerekiyor

        List<Map<String,Object>>allUsersMap= response.body().as(List.class);

        System.out.println("allUsersMap = " + allUsersMap);

        //Ikinci kullanicinin adini assert edelim

        System.out.println("allUsersMap.get(1).get(\"name\") = " + allUsersMap.get(1).get("name"));
        String name= (String) allUsersMap.get(1).get("name");
        Assert.assertEquals(name,"isa akyuz");

        System.out.println("allUsersMap.get(0).get(\"skills\") = " + allUsersMap.get(0).get("skills"));

        List<String> skills= (List<String>) allUsersMap.get(0).get("skills");

        Assert.assertEquals(skills.get(0),"PHP");

        List<Map<String,Object>>experienceListMap= (List<Map<String, Object>>) allUsersMap.get(0).get("experience");

        System.out.println("experienceListMap = " + experienceListMap);

        System.out.println("experienceListMap.get(1).get(\"job\") = " + experienceListMap.get(1).get("job"));
    }


}