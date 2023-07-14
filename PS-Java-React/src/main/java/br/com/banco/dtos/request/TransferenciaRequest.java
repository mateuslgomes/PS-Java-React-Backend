package br.com.banco.dtos.request;

import br.com.banco.enums.Tipo;
import br.com.banco.model.Conta;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransferenciaRequest {

    private Long id;
    private LocalDateTime dataTransferencia;
    private BigDecimal valor;
    private Tipo tipo;
    private String nomeOperadorTransacao;
    private Conta conta;

}
