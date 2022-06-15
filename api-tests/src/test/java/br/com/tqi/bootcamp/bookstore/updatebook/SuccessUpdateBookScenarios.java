package br.com.tqi.bootcamp.bookstore.updatebook;

import br.com.tqi.bootcamp.bookstore.BaseTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.io.File;

import static br.com.tqi.bootcamp.bookstore.Data.AUTHOR;
import static br.com.tqi.bootcamp.bookstore.Data.AUTHOR_2;
import static br.com.tqi.bootcamp.bookstore.Data.AUTHOR_CODE;
import static br.com.tqi.bootcamp.bookstore.Data.AUTHOR_CODE_2;
import static br.com.tqi.bootcamp.bookstore.Data.FILE;
import static br.com.tqi.bootcamp.bookstore.Data.FILE_2;
import static br.com.tqi.bootcamp.bookstore.Data.NAME;
import static br.com.tqi.bootcamp.bookstore.Data.NAME_2;
import static br.com.tqi.bootcamp.bookstore.Data.PRICE;
import static br.com.tqi.bootcamp.bookstore.Data.PRICE_2;
import static io.restassured.RestAssured.given;

public class SuccessUpdateBookScenarios extends BaseTest {

    @Test
    public void shouldUpdateABook() {
        //cria o livro
        Response response = given()
                .auth().basic("bootcamp", "vempratqi")
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
                .body("author", Matchers.is(AUTHOR))
                .extract()
                .response();

        String bookCode = response.path("code");

        //edita o livro
        given()
                .auth().basic("bootcamp", "vempratqi")
                .header("Content-Type", "multipart/form-data")
                .multiPart("name", NAME_2)
                .multiPart("price", PRICE_2)
                .multiPart("author_code", AUTHOR_CODE_2)
                .multiPart("file", new File(FILE_2))
                .when()
                .put(host + "/books" + "/" + bookCode)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("code", Matchers.is(bookCode))
                .body("name", Matchers.is(NAME_2))
                .body("price", Matchers.is(PRICE_2))
                .body("image", Matchers.notNullValue())
                .body("author", Matchers.is(AUTHOR_2));
    }
}








