package com.escola.Turma.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //Declaramos que é uma entidade (Tabela)
@Table(name = "TB_TURMA") //Explicitamos que é uma tabela e seu nome (Obrigatorio pelo JPA)
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Define que o ID será gerado automaticamente pelo banco de dados
    @Column(name = "Turma_id", nullable = false)        // Informa que o campo refere-se a uma coluna da tabela
    private Integer id;                                 // Atributo convencional da classe java.

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
