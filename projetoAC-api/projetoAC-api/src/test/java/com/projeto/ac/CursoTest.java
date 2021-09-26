package com.projeto.ac;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CursoTest {

    private static String uri;
    private static int porta;
    private static String path;

    private static int codCurso;

    @BeforeAll
    public static void preCondicao() {
        uri = RestAssured.baseURI = "http://localhost:";
        porta = RestAssured.port = 8080;
        path = RestAssured.basePath = "/api";
    }

    @Test
    @Tag("apiTestRest")
    @Order(2)
    @DisplayName("Teste GET Cursos Request")
    public void getCursosRequestTest(){
        given()
                .contentType("application/json")
        .when()
                .get(uri + porta + path +"/cursos")
        .then()
                .statusCode(200);
    }

    @Test
    @Tag("apiTestRest")
    @Order(3)
    @DisplayName("Teste GET Curso Request")
    public void getCursoRequestTest(){
        codCurso = 1;

        given()
                .contentType("application/json")
        .when()
                .get(uri + porta + path + "/cursos/" + codCurso)
        .then()
                .statusCode(200);
    }

    @Test
    @Tag("apiTestRest")
    @Order(1)
    @RepeatedTest(1)
    @DisplayName("Teste POST Curso Request")
    public void postCursoRequestTest(){

        String cursoJson = "{\n" +
                "        \"nome\": \"Medicina\",\n" +
                "        \"cargaHoraria\": 6000,\n" +
                "        \"status\": \"T\"\n" +
                "}";

        given()
                .contentType("application/json").body(cursoJson)
        .when()
                .post(uri + porta +path + "/cursos")
        .then()
                .statusCode(201);
    }

    @Test
    @Tag("apiTestRest")
    @Order(4)
    @DisplayName("Teste PUT Curso Request")
    public void putCursoRequestTest(){

        codCurso = 1;

        String cursoJson = "{\n" +
                "        \"codCurso\": 3,\n" +
                "        \"nome\": \"Sistemas de Informação\",\n" +
                "        \"cargaHoraria\": 12,\n" +
                "        \"status\": \"F\"\n" +
                "    }";

        given()
                .contentType("application/json")
                .body(cursoJson)
        .when()
                .put(uri + porta + path + "/cursos/" + codCurso)
        .then()
                .statusCode(200);
    }

    @Test
    @Tag("apiTestRest")
    @Order(5)
    @DisplayName("Teste DELETE Curso Request")
    public void deleteCursoRequestTest(){
        codCurso = 1;

        given()
                .contentType("application/json")
        .when()
                .delete(uri + porta + path + "/cursos/" + codCurso)
        .then()
                .statusCode(200);
    }

    @Test
    @Tag("apiTestRest")
    @Order(6)
    @DisplayName("Teste GET Curso Request Não Encontrado")
    public void getCursoRequestTestNaoEncontrado(){
        codCurso = 0;

        given()
                .contentType("application/json")
                .when()
                .get(uri + porta + path + "/cursos/" + codCurso)
                .then()
                .statusCode(404);
    }

    @Test
    @Tag("apiTestRest")
    @Order(7)
    @DisplayName("Teste POST Curso Bad Request")
    public void postCursoBadRequestTest(){

        String cursoJson = "{\n" +
                "        \"nome\": \"Elétrica\",\n" +
                "        \"cargaHoraria\": ,\n" +
                "        \"status\": \"T\"\n" +
                "}";

        given()
                .contentType("application/json").body(cursoJson)
                .when()
                .post(uri + porta +path + "/cursos")
                .then()
                .statusCode(400);
    }
}
