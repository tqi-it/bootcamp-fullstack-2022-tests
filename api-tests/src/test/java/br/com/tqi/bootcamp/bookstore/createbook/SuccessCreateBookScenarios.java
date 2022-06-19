package br.com.tqi.bootcamp.bookstore.createbook;

import br.com.tqi.bootcamp.bookstore.BaseTest;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.io.File;

import static br.com.tqi.bootcamp.bookstore.Data.AUTHOR;
import static br.com.tqi.bootcamp.bookstore.Data.AUTHOR_CODE;
import static br.com.tqi.bootcamp.bookstore.Data.FILE;
import static br.com.tqi.bootcamp.bookstore.Data.NAME;
import static br.com.tqi.bootcamp.bookstore.Data.PASSWORD;
import static br.com.tqi.bootcamp.bookstore.Data.PRICE;
import static br.com.tqi.bootcamp.bookstore.Data.USER_NAME;

public class SuccessCreateBookScenarios extends BaseTest {

    @Test
    public void shouldCreateABook() {
        RestAssured.given()
                .auth().basic(USER_NAME, PASSWORD)
                .header("Content-Type", "multipart/form-data")
                .multiPart("name", NAME)
                .multiPart("price", PRICE)
                .multiPart("author_code", AUTHOR_CODE)
                .multiPart("file", new File(FILE))
                .when()
                .post(host + "/books")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("code", Matchers.notNullValue())
                .body("name", Matchers.is(NAME))
                .body("price", Matchers.is(PRICE))
                .body("image", Matchers.notNullValue())
                .body("author", Matchers.is(AUTHOR));
    }

}
