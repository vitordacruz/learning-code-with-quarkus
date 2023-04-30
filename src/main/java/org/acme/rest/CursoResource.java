package org.acme.rest;

import org.acme.domain.dto.AlunoResponse;
import org.acme.domain.dto.CursoParam;
import org.acme.domain.dto.CursoRequest;
import org.acme.domain.dto.CursoResponse;
import org.acme.domain.repository.CursoRepository;
import org.acme.usercase.CursoService;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class CursoResource implements CursoApi {

    @Inject
    private CursoService cursoService;

    @Override
    public Response findAll(CursoParam parameter) {
        if (parameter.getNome() == null && parameter.getId() == null) {
            return Response.ok(cursoService.findAll()).build();
        } else {
            return Response.ok().entity(cursoService.findCursos(parameter)).build();
        }
    }

    @Override
    public Response createCurso(CursoRequest cursoRequest) {
        CursoResponse cursoSalvo = cursoService.save(cursoRequest);
        URI uri = UriBuilder.fromPath("/cursos/{id}").build(cursoSalvo.getId());
        return Response.created(uri).entity(cursoSalvo).build();
    }
}
