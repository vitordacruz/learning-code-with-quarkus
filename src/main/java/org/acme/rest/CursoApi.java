package org.acme.rest;

import org.acme.domain.dto.AlunoParameter;
import org.acme.domain.dto.AlunoRequest;
import org.acme.domain.dto.CursoParam;
import org.acme.domain.dto.CursoRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/cursos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface CursoApi {

    @GET
    Response findAll(CursoParam param);

    @POST
    Response createAluno(CursoRequest aluno);
}
