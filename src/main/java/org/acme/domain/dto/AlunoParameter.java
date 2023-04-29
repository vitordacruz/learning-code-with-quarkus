package org.acme.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.ws.rs.QueryParam;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoParameter {
    @QueryParam("id")
    private Long id;
    @QueryParam("nome")
    private String nome;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @QueryParam("dataNascimento")
    private LocalDate dataNascimento;
}
