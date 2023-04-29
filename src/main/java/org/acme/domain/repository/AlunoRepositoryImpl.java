package org.acme.domain.repository;

import org.acme.domain.Aluno;
import org.acme.domain.dto.AlunoParameter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class AlunoRepositoryImpl implements AlunoRepository {
    public static final String PARAMETER_SYMBOL = " :";

    public static final String EQUAL_SYMBOL = " = ";
    @Inject
    EntityManager em;

    public static final String ID_FIELD =  "id";
    public static final String NOME_FIELD = "nome";
    public static final String DATA_NASCIMENTO_FIELD = "dataNascimento";

    public static final String AND_CLAUSE = " and ";

    @Override
    public List<Aluno> findAlunos(AlunoParameter alunoParameter) {
        Map<String, Object> parameters = new HashMap<>();
        final var ID_PARAMETER = "id";
        final var NOME_PARAMETER = "nome";
        final var DATA_NASCIMENTO_PARAMETER = "dataNascimento";

        var query = "";

        if (alunoParameter.getId() != null) {
            if (!parameters.isEmpty()) {
                query += AND_CLAUSE;
            }
            query += ID_FIELD  + EQUAL_SYMBOL + PARAMETER_SYMBOL + ID_PARAMETER;
            parameters.put(ID_PARAMETER, alunoParameter.getId());
        }

        if (alunoParameter.getNome() != null) {
            if (!parameters.isEmpty()) {
                query += AND_CLAUSE;
            }
            query += NOME_FIELD + EQUAL_SYMBOL + PARAMETER_SYMBOL + NOME_PARAMETER;
            parameters.put(NOME_PARAMETER, alunoParameter.getNome());
        }

        if (alunoParameter.getDataNascimento() != null) {
            if (!parameters.isEmpty()) {
                query += AND_CLAUSE;
            }
            query += DATA_NASCIMENTO_FIELD + EQUAL_SYMBOL + PARAMETER_SYMBOL + DATA_NASCIMENTO_PARAMETER;
            parameters.put(DATA_NASCIMENTO_PARAMETER, alunoParameter.getDataNascimento());
        }

        return list(query, parameters);
    }

}
