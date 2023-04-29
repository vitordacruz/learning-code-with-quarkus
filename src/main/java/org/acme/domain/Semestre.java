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
public class Semestre {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Curso curso;
    private Integer ano;
    private Integer semestre;
    @OneToMany(mappedBy="semestre")
    private List<Materia> materias;
}
