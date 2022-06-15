package br.com.tqi.bootcamp.bookstore;

import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

    protected static String host = "";

    @BeforeAll
    static void setUp() {
        host = "http://localhost:8080";
    }

}
