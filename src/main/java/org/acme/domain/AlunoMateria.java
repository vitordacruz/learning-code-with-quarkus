package org.acme.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoMateria {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Aluno aluno;
    @ManyToOne
    private Materia materia;
}
