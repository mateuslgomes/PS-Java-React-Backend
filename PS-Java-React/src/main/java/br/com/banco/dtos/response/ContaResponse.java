package br.com.banco.dtos.response;

import br.com.banco.model.Conta;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContaResponse {

    private Long idConta;
    private String nomeResponsavel;

    public static ContaResponse of(Conta conta) {
        return ContaResponse.builder()
                .idConta(conta.getIdConta())
                .nomeResponsavel(conta.getNomeResponsavel())
                .build();
    }

}
