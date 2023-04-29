package org.acme.domain.mapper;

import org.acme.domain.Aluno;
import org.acme.domain.dto.AlunoRequest;
import org.acme.domain.dto.AlunoResponse;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = QuarkusMappingConfig.class)
public interface AlunoMapper {

    List<AlunoResponse> toAlunoResponseList(List<Aluno> alunos);
    AlunoResponse toAlunoResponse(Aluno entity);
    Aluno toEntity(AlunoRequest alunoRequest);

}
