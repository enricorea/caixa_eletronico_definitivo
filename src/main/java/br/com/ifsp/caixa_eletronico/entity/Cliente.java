package br.com.ifsp.caixa_eletronico.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

// @Data cria get e set e toString automaticamente
// @AllArgsConstructor cria construtor com as propriedades da classe Cliente
// @NoArgsConstructor cria construtor vazio
// @Builder ajuda na criação de objetos Cliente
// @Entity informa que é uma entidade de banco de dados

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class Cliente implements Serializable{

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "NUMERO_CONTA", nullable = false, unique = true)
        private String numeroConta;

        @Column(name = "PIN")
        private String pin;

        @Column(name = "NOME")
        private String nome;

        @Column(name = "SALDO")
        private BigDecimal saldo;
}
