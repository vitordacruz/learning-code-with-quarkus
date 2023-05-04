package org.acme.usercase;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.acme.domain.Aluno;
import org.acme.domain.dto.AlunoRequest;
import org.acme.domain.dto.AlunoResponse;
import org.acme.domain.repository.AlunoRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import javax.inject.Inject;

@QuarkusTest
public class AlunoServiceTest {

    @Inject
    AlunoService alunoService;

    @InjectMock
    AlunoRepository alunoRepository;

    @Test
    public void testFindAllAlunos() {
        var dataNascimento  = LocalDate.of(2000, 7, 28);

        final LocalDate dataNascimento2 = LocalDate.of(2010, 6, 15);
        final Long id = 1L;
        final Long id2 = 2L;
        final String nome = "Vitor";
        final String nome2 = "Renan";

        List<Aluno> alunos =
                List.of(
                        new Aluno(id, nome, dataNascimento),
                        new Aluno(id2, nome2, dataNascimento2)
                );

        Mockito
                .when(alunoRepository.listAll())
                .thenReturn(alunos);


        List<AlunoResponse> resposta = alunoService.findAll();

        Assertions.assertEquals(resposta.get(0).getId().intValue(), id.intValue());
        Assertions.assertEquals(resposta.get(1).getId().intValue(), id2.intValue());
        Assertions.assertEquals(resposta.get(0).getNome(), nome);
        Assertions.assertEquals(resposta.get(1).getNome(), nome2);
        Assertions.assertEquals(resposta.get(0).getDataNascimento(), dataNascimento);
        Assertions.assertEquals(resposta.get(1).getDataNascimento(), dataNascimento2);

        Mockito.verify(alunoRepository, Mockito.times(1))
                .listAll();

    }
}
