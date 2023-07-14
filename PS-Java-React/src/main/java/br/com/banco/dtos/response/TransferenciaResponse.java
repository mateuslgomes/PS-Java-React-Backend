package br.com.banco.dtos.response;

import br.com.banco.enums.Tipo;
import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransferenciaResponse {

    private Long id;
    private LocalDateTime dataTransferencia;
    private BigDecimal valor;
    private Tipo tipo;
    private String nomeOperadorTransacao;
    private Conta conta;

    public static TransferenciaResponse of(Transferencia transferencia) {
        return TransferenciaResponse.builder()
                .id(transferencia.getId())
                .dataTransferencia(transferencia.getDataTransferencia())
                .valor(transferencia.getValor())
                .tipo(transferencia.getTipo())
                .nomeOperadorTransacao(transferencia.getNomeOperadorTransacao())
                .conta(transferencia.getConta())
                .build();
    }

}
