package br.com.banco.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConta;

    @Column(nullable = false)
    private String nomeResponsavel;

}

