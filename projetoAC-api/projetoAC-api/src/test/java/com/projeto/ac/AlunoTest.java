package com.projeto.ac;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

public class AlunoTest {

    private static String uri;
    private static int porta;
    private static String path;

    @BeforeAll
    public static void preCondicao() {
        uri = RestAssured.baseURI = "http://localhost:";
        porta = RestAssured.port = 8080;
        path = RestAssured.basePath = "/api";
    }

    @Test
    @Tag("apiTestRest")
    @Order(2)
    @DisplayName("Teste GET Alunos Request")
    public void getAlunosRequestTest(){
        given()
                .contentType("application/json")
                .when()
                .get(uri + porta + path +"/alunos")
                .then()
                .statusCode(200);
    }

    @Test
    @Tag("apiTestRest")
    @Order(3)
    @DisplayName("Teste GET Aluno Request")
    public void getAlunoRequestTest(){
        int codAluno = 2;

        given()
                .contentType("application/json")
                .when()
                .get(uri + porta + path + "/alunos/" + codAluno)
                .then()
                .statusCode(200);
    }

    @Test
    @Tag("apiTestRest")
    @Order(1)
    @RepeatedTest(1)
    @DisplayName("Teste POST Aluno Request")
    public void postAlunoRequestTest(){

        String alunoJson = "{\n" +
                "        \"nome\": \"Pedro Junio\",\n" +
                "        \"email\": \"pedroj@email.com\",\n" +
                "        \"celular\": \"1234567890\",\n" +
                "        \"dataNascimento\": \"2021-03-31T00:00:00.000+00:00\",\n" +
                "        \"status\": \"T\",\n" +
                "        \"curso\": {\n" +
                "            \"codCurso\": 1,\n" +
                "            \"nome\": \"Teste\",\n" +
                "            \"cargaHoraria\": 100,\n" +
                "            \"status\": \"F\"\n" +
                "        }\n" +
                "}";

        given()
                .contentType("application/json").body(alunoJson)
                .when()
                .post(uri + porta +path + "/alunos")
                .then()
                .statusCode(201);
    }

    @Test
    @Tag("apiTestRest")
    @Order(4)
    @DisplayName("Teste PUT Aluno Request")
    public void putAlunoRequestTest(){

        int codAluno = 3;

        String alunoJson = "{\n" +
                "        \"nome\": \"Pedro Ferreira\",\n" +
                "        \"email\": \"pedrof@email.com\",\n" +
                "        \"celular\": \"0000000000\",\n" +
                "        \"dataNascimento\": \"2021-03-31T00:00:00.000+00:00\",\n" +
                "        \"status\": \"F\",\n" +
                "        \"curso\": {\n" +
                "            \"codCurso\": 3,\n" +
                "            \"nome\": \"Teste\",\n" +
                "            \"cargaHoraria\": 100,\n" +
                "            \"status\": \"F\"\n" +
                "        }\n" +
                "}";

        given()
                .contentType("application/json")
                .body(alunoJson)
                .when()
                .put(uri + porta + path + "/alunos/" + codAluno)
                .then()
                .statusCode(200);
    }

    @Test
    @Tag("apiTestRest")
    @Order(5)
    @DisplayName("Teste DELETE Aluno Request")
    public void deleteAlunoRequestTest(){
        int codAluno = 4;

        given()
                .contentType("application/json")
                .when()
                .delete(uri + porta + path + "/alunos/" + codAluno)
                .then()
                .statusCode(200);
    }
}
