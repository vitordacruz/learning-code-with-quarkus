package org.acme;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.acme.domain.dto.AlunoResponse;
import org.acme.rest.AlunoResource;
import org.acme.usercase.AlunoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(AlunoResource.class)
public class AlunoResourceTest {


    @InjectMock
    AlunoService alunoService;

    @Test
    public void testFindAllEndpoint() {

        final LocalDate dataNascimento = LocalDate.of(2000, 8, 20);
        final LocalDate dataNascimento2 = LocalDate.of(2010, 6, 15);
        final Long id = 1L;
        final Long id2 = 2L;
        final String nome = "Vitor";
        final String nome2 = "Renan";

        List<AlunoResponse> alunos =
                List.of(
                        new AlunoResponse(id, nome, dataNascimento),
                        new AlunoResponse(id2, nome2, dataNascimento2)
                );

        Mockito
                .when(alunoService.findAll())
                        .thenReturn(alunos);

        AlunoResponse[] resposta = given()
          .when().get()
          .then()
             .statusCode(200)
             .extract().body().as(AlunoResponse[].class);

        Assertions.assertEquals(resposta[0].getId().intValue(), id.intValue());
        Assertions.assertEquals(resposta[1].getId().intValue(), id2.intValue());
        Assertions.assertEquals(resposta[0].getNome(), nome);
        Assertions.assertEquals(resposta[1].getNome(), nome2);
        Assertions.assertEquals(resposta[0].getDataNascimento(), dataNascimento);
        Assertions.assertEquals(resposta[1].getDataNascimento(), dataNascimento2);

    }

}