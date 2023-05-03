package org.acme.rest;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.acme.domain.dto.*;
import org.acme.rest.CursoResource;
import org.acme.usercase.CursoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
@TestHTTPEndpoint(CursoResource.class)
public class CursoResourceTest {

    @InjectMock
    CursoService cursoService;

    @Test
    public void testFindAllCursosEndpoint() {

        final Long id = 1L;
        final Long id2 = 2L;
        final String nome = "Matemática";
        final String nome2 = "Fisica";

        List<CursoResponse> cursos =
                List.of(
                        new CursoResponse(id, nome),
                        new CursoResponse(id2, nome2)
                );

        Mockito
                .when(cursoService.findAll())
                .thenReturn(cursos);

        CursoResponse[] resposta = given()
                .when().get()
                .then()
                .contentType(MediaType.APPLICATION_JSON)
                .statusCode(200)
                .extract().body().as(CursoResponse[].class);

        Assertions.assertEquals(resposta[0].getId().intValue(), id.intValue());
        Assertions.assertEquals(resposta[1].getId().intValue(), id2.intValue());
        Assertions.assertEquals(resposta[0].getNome(), nome);
        Assertions.assertEquals(resposta[1].getNome(), nome2);

        Mockito.verify(cursoService, Mockito.times(1))
                .findAll();

    }

    @Test
    public void testFindCursosFilteredEndpoint() {
        final Long id = 1L;
        final Long id2 = 2L;
        final String nome = "Matemática";

        List<CursoResponse> cursos =
                List.of(
                        new CursoResponse(id, nome),
                        new CursoResponse(id2, nome)
                );

        var parameter = new AlunoParameter();

        parameter.setNome(nome);

        Mockito
                .when(cursoService.findCursos(any(CursoParam.class)))
                .thenReturn(cursos);

        CursoResponse[] resposta = given()
                .when().get("?nome=" + parameter.getNome())
                .then()
                .contentType(MediaType.APPLICATION_JSON)
                .statusCode(200)
                .extract().body().as(CursoResponse[].class);

        Assertions.assertEquals(resposta[0].getId(), id);
        Assertions.assertEquals(resposta[1].getId(), id2);
        Assertions.assertEquals(resposta[0].getNome(), nome);
        Assertions.assertEquals(resposta[0].getNome(), resposta[1].getNome());

        Mockito.verify(cursoService, Mockito.times(1))
                .findCursos(any(CursoParam.class));

    }

    @Test
    public void testCreateAlunoEndpoint() {
        final Long id = 1L;
        final String nome = "Matemática";

        CursoRequest request = new CursoRequest(null, nome);

        Mockito
                .when(cursoService.save(any(CursoRequest.class)))
                .thenReturn(new CursoResponse(id, nome));

        CursoResponse response = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .when()
                .post()
                .then()
                .contentType(MediaType.APPLICATION_JSON)
                .statusCode(201)
                .extract().body().as(CursoResponse.class);

        Assertions.assertEquals(response.getId(), id);
        Assertions.assertEquals(response.getNome(), nome);

    }

}
