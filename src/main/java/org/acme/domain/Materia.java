package org.acme.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Materia {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Curso curso;
    @ManyToOne
    private Semestre semestre;
    @OneToMany(mappedBy="aluno")
    private List<AlunoMateria> alunos;
}
