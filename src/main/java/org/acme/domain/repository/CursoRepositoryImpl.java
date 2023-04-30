package org.acme.domain.repository;

import org.acme.domain.Curso;
import org.acme.domain.dto.CursoParam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.acme.domain.repository.QueryUtils.*;

@ApplicationScoped
public class CursoRepositoryImpl implements CursoRepository {

    @Inject
    EntityManager em;

    public static final String ID_FIELD =  "id";
    public static final String NOME_FIELD = "nome";

    public List<Curso> findAlunos(CursoParam cursoParameter) {
        Map<String, Object> parameters = new HashMap<>();
        final var ID_PARAMETER = "id";
        final var NOME_PARAMETER = "nome";

        var query = "";

        if (cursoParameter.getId() != null) {
            if (!parameters.isEmpty()) {
                query += AND_CLAUSE;
            }
            query += ID_FIELD  + EQUAL_SYMBOL + PARAMETER_SYMBOL + ID_PARAMETER;
            parameters.put(ID_PARAMETER, cursoParameter.getId());
        }

        if (cursoParameter.getNome() != null) {
            if (!parameters.isEmpty()) {
                query += AND_CLAUSE;
            }
            query += NOME_FIELD + EQUAL_SYMBOL + PARAMETER_SYMBOL + NOME_PARAMETER;
            parameters.put(NOME_PARAMETER, cursoParameter.getNome());
        }

        return list(query, parameters);
    }
}
