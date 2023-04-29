package org.acme.domain.mapper;

import org.acme.domain.Aluno;
import org.acme.domain.Curso;
import org.acme.domain.dto.AlunoRequest;
import org.acme.domain.dto.AlunoResponse;
import org.acme.domain.dto.CursoRequest;
import org.acme.domain.dto.CursoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = QuarkusMappingConfig.class)
public interface CursoMapper {
    List<CursoResponse> toCursoResponseList(List<Curso> cursos);
    CursoResponse toCursoResponse(Curso entity);
    Curso toEntity(CursoRequest cursoRequest);
}
