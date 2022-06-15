package br.com.tqi.bootcamp.bookstore.createbook;

import br.com.tqi.bootcamp.bookstore.BaseTest;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;

import static br.com.tqi.bootcamp.bookstore.Data.AUTHOR_CODE;
import static br.com.tqi.bootcamp.bookstore.Data.FILE;
import static br.com.tqi.bootcamp.bookstore.Data.INVALID_NAME_LENGTH;
import static br.com.tqi.bootcamp.bookstore.Data.MUST_HAVE_VALUE;
import static br.com.tqi.bootcamp.bookstore.Data.PRICE;
import static br.com.tqi.bootcamp.bookstore.Data.REQUEST_FIELD_NAME;
import static br.com.tqi.bootcamp.bookstore.Data.STRING_WITH_101_CHARACTERS;
import static br.com.tqi.bootcamp.bookstore.Data.STRING_WITH_4_CHARACTERS;
import static io.restassured.RestAssured.given;

public class BadRequestCreateBookScenarios  extends BaseTest {

    @Test
    public void shouldNotCreateBookWithoutNameField() {
        given()
                .auth().basic("bootcamp", "vempratqi")
                .header("Content-Type", "multipart/form-data")
                .multiPart("price", PRICE)
                .multiPart("author_code", AUTHOR_CODE)
                .multiPart("file", new File(FILE))
                .when()
                .post(host + "/books")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("errors[0].parameter", Matchers.is(REQUEST_FIELD_NAME))
                .body("errors[0].message", Matchers.is(MUST_HAVE_VALUE));
    }

    @ParameterizedTest
    @ValueSource(strings = {STRING_WITH_4_CHARACTERS, STRING_WITH_101_CHARACTERS})
    public void shouldNotCreateBookWithInvalidNameLength(String name) {
        given()
                .auth().basic("bootcamp", "vempratqi")
                .header("Content-Type", "multipart/form-data")
                .multiPart("name", name)
                .multiPart("price", PRICE)
                .multiPart("author_code", AUTHOR_CODE)
                .multiPart("file", new File(FILE))
                .when()
                .post(host + "/books")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("errors[0].parameter", Matchers.is(REQUEST_FIELD_NAME))
                .body("errors[0].message", Matchers.is(INVALID_NAME_LENGTH));
    }

}
