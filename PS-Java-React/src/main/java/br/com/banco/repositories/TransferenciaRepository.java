package br.com.banco.repositories;

import br.com.banco.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

    List<Transferencia> findByContaIdConta(Long idConta);

    List<Transferencia> findByDataTransferenciaBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

    List<Transferencia> findByNomeOperadorTransacao(String nomeOperador);

    List<Transferencia> findByNomeOperadorTransacaoAndDataTransferenciaBetween(String nomeOperador, LocalDateTime
            dataInicio, LocalDateTime dataFim);

}
