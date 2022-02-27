package apiMethods;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetEmailFromURL {
    public static Boolean getEmailFromURL(String fistName, String secondName) {
        given()
                .when()
                .contentType(ContentType.JSON)
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("data.find{it.first_name =='" + fistName + "'}.email", equalTo("michael.lawson@reqres.in"))
                .and()
                .body("data.find{it.last_name =='" + secondName + "'}.email", equalTo("michael.lawson@reqres.in"));
        return Boolean.TRUE;
    }

}
