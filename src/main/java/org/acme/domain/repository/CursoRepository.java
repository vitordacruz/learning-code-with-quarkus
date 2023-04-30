package org.acme.domain.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.domain.Aluno;
import org.acme.domain.Curso;
import org.acme.domain.dto.AlunoParameter;
import org.acme.domain.dto.CursoParam;

import java.util.List;

public interface CursoRepository extends PanacheRepository<Curso>  {

    public List<Curso> findAlunos(CursoParam cursoParameter);
}
