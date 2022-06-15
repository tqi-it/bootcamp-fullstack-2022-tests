package br.com.tqi.bootcamp.bookstore.authorization;

import br.com.tqi.bootcamp.bookstore.BaseTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.io.File;

import static br.com.tqi.bootcamp.bookstore.Data.AUTHOR_CODE;
import static br.com.tqi.bootcamp.bookstore.Data.FILE;
import static br.com.tqi.bootcamp.bookstore.Data.NAME;
import static br.com.tqi.bootcamp.bookstore.Data.PRICE;
import static io.restassured.RestAssured.given;

public class AuthorizationScenarios extends BaseTest {

    @Test
    public void shouldReturnUnauthorizedWhenUsernameIsInvalid() {
        given()
                .auth().basic("bootcamp4", "vempratqi")
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
