package com.escola.Turma.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_TURMA")
public class Turma {

    @Column(name = "TurmaId", nullable = false)
    private Integer id;

    @Column(name = "Descricao", nullable = false, length = 255, unique = true)
    private String descricao;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}
