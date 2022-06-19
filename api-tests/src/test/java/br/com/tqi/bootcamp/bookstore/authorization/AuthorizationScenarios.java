package br.com.tqi.bootcamp.bookstore.authorization;

import br.com.tqi.bootcamp.bookstore.BaseTest;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.io.File;

import static br.com.tqi.bootcamp.bookstore.Data.AUTHOR_CODE;
import static br.com.tqi.bootcamp.bookstore.Data.FILE;
import static br.com.tqi.bootcamp.bookstore.Data.INVALID_USER_NAME;
import static br.com.tqi.bootcamp.bookstore.Data.NAME;
import static br.com.tqi.bootcamp.bookstore.Data.PASSWORD;
import static br.com.tqi.bootcamp.bookstore.Data.PRICE;

public class AuthorizationScenarios extends BaseTest {

    @Test
    public void shouldReturnUnauthorizedWhenUsernameIsInvalid() {
        RestAssured.given()
                .auth().basic(INVALID_USER_NAME, PASSWORD)
                .header("Content-Type", "multipart/form-data")
                .multiPart("name", NAME)
                .multiPart("price", PRICE)
                .multiPart("author_code", AUTHOR_CODE)
                .multiPart("file", new File(FILE))
                .when()
                .post(host + "/books")
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

}
