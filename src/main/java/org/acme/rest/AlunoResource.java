package org.acme.rest;

import org.acme.domain.dto.AlunoParameter;
import org.acme.domain.dto.AlunoRequest;
import org.acme.domain.dto.AlunoResponse;
import org.acme.usercase.AlunoService;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;


public class AlunoResource implements AlunoApi {

    @Inject
    AlunoService alunoService;

    @Override
    public Response findAll(AlunoParameter parameter) {
        if (parameter.getDataNascimento() == null && parameter.getNome() == null && parameter.getId() == null) {
            return Response.ok(alunoService.findAll()).build();
        } else {
            return Response.ok().entity(alunoService.findAlunos(parameter)).build();
        }
    }

    @Override
    public Response createAluno(AlunoRequest aluno) {
        AlunoResponse alunoSalvo = alunoService.save(aluno);
        URI uri = UriBuilder.fromPath("/alunos/{id}").build(alunoSalvo.getId());
        return Response.created(uri).entity(alunoSalvo).build();
    }

}
