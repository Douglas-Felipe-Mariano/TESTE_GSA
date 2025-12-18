package com.escola.Turma.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity //Declaramos que é uma entidade (Tabela)
@Table(name = "TB_ALUNO") //Explicitamos que é uma tabela e seu nome (Obrigatorio pelo JPA)
public class Aluno {

    @Id //Informa que o campo é um ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Define que o ID será gerado automaticamente pelo banco de dados
    @Column(name = "AlunoId", nullable = false)         // Informa que o campo refere-se a uma coluna da tabela
    private Integer id;

    @ManyToOne //Define que muitos alunos podem ter uma turma
    @JoinColumn(name = "TurmaId", nullable = false) // Efetua o join de relacionamento
    private Turma turma;

    @Column(name = "Nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "DataNascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "Endereco", nullable = true, length = 255)
    private String endereco;

    @Column(name = "DataCadastro", nullable = false, updatable = false)
    private LocalDate dataCadastro = LocalDate.now();

    @Column(name = "Ativo", nullable = false)
    private boolean ativo = true;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Turma getTurma() {
        return this.turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public LocalDate getDataCadastro() {
        return this.dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }


    public boolean isAtivo() {
        return this.ativo;
    }

    public boolean getAtivo() {
        return this.ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }


}
