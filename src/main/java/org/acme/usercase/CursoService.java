package org.acme.usercase;

import org.acme.domain.dto.*;
import org.acme.domain.mapper.CursoMapper;
import org.acme.domain.repository.CursoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CursoService {

    @Inject
    CursoRepository cursoRepository;
    @Inject
    CursoMapper cursoMapper;

    @Transactional
    public CursoResponse save(CursoRequest cursoRequest) {
        var curso = cursoMapper.toEntity(cursoRequest);
        cursoRepository.persist(curso);
        CursoResponse cursoResponse =  cursoMapper.toCursoResponse(curso);
        return cursoResponse;
    }

    public List<CursoResponse> findAll() {
        return cursoRepository.listAll().stream().map(cursoMapper::toCursoResponse).collect(Collectors.toList());
    }

    public List<CursoResponse> findCursos(CursoParam cursoParameter) {
        return cursoRepository.findCursos(cursoParameter)
                .stream().map(cursoMapper::toCursoResponse).collect(Collectors.toList());
    }

}
