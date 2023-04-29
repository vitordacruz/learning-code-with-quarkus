package org.acme.rest;

import org.acme.domain.dto.AlunoParameter;
import org.acme.domain.dto.AlunoRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/alunos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface AlunoApi {

    @GET
    Response findAll(@BeanParam AlunoParameter parameter);

    @POST
    Response createAluno(AlunoRequest aluno);

}
