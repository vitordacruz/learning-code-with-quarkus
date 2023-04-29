package org.acme.usercase;

import org.acme.domain.Aluno;
import org.acme.domain.dto.AlunoParameter;
import org.acme.domain.dto.AlunoRequest;
import org.acme.domain.dto.AlunoResponse;
import org.acme.domain.mapper.AlunoMapper;
import org.acme.domain.repository.AlunoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class AlunoService {

    @Inject
    AlunoRepository alunoRepository;

    @Inject
    AlunoMapper alunoMapper;

    @Transactional
    public AlunoResponse save(AlunoRequest alunoRequest) {
        var aluno = alunoMapper.toEntity(alunoRequest);
        alunoRepository.persist(aluno);
        AlunoResponse alunoResponse =  alunoMapper.toAlunoResponse(aluno);
        return alunoResponse;
    }

    public List<AlunoResponse> findAll() {
        return alunoRepository.listAll().stream().map(alunoMapper::toAlunoResponse).collect(Collectors.toList());
    }

    public List<AlunoResponse> findAlunos(AlunoParameter alunoParameter) {
        return alunoRepository.findAlunos(alunoParameter)
                .stream().map(alunoMapper::toAlunoResponse).collect(Collectors.toList());
    }

}
