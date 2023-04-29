package org.acme.domain.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.domain.Aluno;
import org.acme.domain.dto.AlunoParameter;

import java.util.List;

public interface AlunoRepository extends PanacheRepository<Aluno> {
    public List<Aluno> findAlunos(AlunoParameter alunoParameter);
}
