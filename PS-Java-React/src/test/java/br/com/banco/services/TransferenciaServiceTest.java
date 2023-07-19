package br.com.banco.services;

import br.com.banco.dtos.response.TransferenciaResponse;
import br.com.banco.enums.Tipo;
import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TransferenciaServiceTest {

    @InjectMocks
    private TransferenciaService service;

    @Mock
    private TransferenciaRepository repository;

    private Conta conta1;
    private Conta conta2;

    private Transferencia transferenciaUm;
    private Transferencia transferenciaDois;
    private Transferencia transferenciaTres;
    private Transferencia transferenciaQuatro;
    private Transferencia transferenciaCinco;
    private Transferencia transferenciaSeis;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startConta();
        startTransferencia();
    }


    @Test
    void DeveRetornarUmaListaDeTransferenciaResponse() {

        List<Transferencia> transferencias = Arrays.asList(transferenciaUm, transferenciaDois, transferenciaTres,
                transferenciaQuatro, transferenciaCinco, transferenciaSeis);

        when(repository.findAll()).thenReturn(transferencias);

        List<TransferenciaResponse> responses = service.findAll();

        assertEquals(6, responses.size());

    }

    @Test
    void DeveRetornarUmaListaDeTransferenciaResponsePorIdDaConta() {
        Long id = 1L;

        List<Transferencia> transferencias = List.of(transferenciaUm, transferenciaTres, transferenciaCinco);

        when(repository.findByContaIdConta(id)).thenReturn(transferencias);

        List<TransferenciaResponse> responses = service.findById(id);

        assertEquals(3, responses.size());

        assertEquals(transferenciaUm, transferencias.get(0));

        assertEquals(new BigDecimal("30895.46"), transferencias.get(0).getValor());

    }

    @Test
    void DeveRetornarUmaListaDeTransferenciaResponseDeAcordoComFiltroData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        LocalDateTime dataInicio = LocalDateTime.parse("2019-05-01T00:00:00", formatter);
        LocalDateTime dataFim = LocalDateTime.parse("2018-05-29T00:00:00", formatter);

        List<Transferencia> transferencias = List.of(transferenciaUm, transferenciaDois);

        when(repository.findByDataTransferenciaBetween(dataInicio, dataFim)).thenReturn(transferencias);

        List<TransferenciaResponse> responses = service.findByDataTransferenciaBetween(dataInicio, dataFim);

        assertEquals(2, responses.size());

    }

    @Test
    void RetornarUmaListaDeTransferenciaResponseDeAcordoONomeDoOperador() {

        String operador = "Beltrano";

        List<Transferencia> transferencias = List.of(transferenciaCinco);

        when(repository.findByNomeOperadorTransacao(operador)).thenReturn(transferencias);

        List<TransferenciaResponse> responses = service.findByNomeOperadorTrasacao(operador);

        assertEquals(1, responses.size());

        assertEquals(transferenciaCinco.getNomeOperadorTransacao(), responses.get(0).getNomeOperadorTransacao());

        assertEquals(transferenciaCinco.getNomeOperadorTransacao(), operador);

    }

    @Test
    void DeveRetornarUmaListaDeTransferenciaResponsePorNomeOperadorEPeriodo() {
        String operador = "Beltrano";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        LocalDateTime dataInicio = LocalDateTime.parse("2019-05-01T00:00:00", formatter);
        LocalDateTime dataFim = LocalDateTime.parse("2018-05-29T00:00:00", formatter);

        List<Transferencia> transferencias = List.of(transferenciaCinco);

        when(repository.findByNomeOperadorTransacaoAndDataTransferenciaBetween(operador, dataInicio, dataFim))
                .thenReturn(transferencias);

        List<TransferenciaResponse> response =
                service.findByNomeOperadorTrasacaoAndDataTransferenciaBetween(operador, dataInicio, dataFim);

        assertEquals(1, response.size());

        assertEquals(transferenciaCinco.getNomeOperadorTransacao(), response.get(0).getNomeOperadorTransacao());

        assertEquals(transferenciaCinco.getNomeOperadorTransacao(), operador);

    }

    void startTransferencia() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZ");

        transferenciaUm = new Transferencia(1L, LocalDateTime.parse("2019-01-01 12:00:00+0300", formatter), new BigDecimal("30895.46"),
                Tipo.TRANSFERENCIA, null, conta1);
        transferenciaDois = new Transferencia(2L, LocalDateTime.parse("2019-02-03 09:53:27+0300", formatter), new BigDecimal("12.24"),
                Tipo.TRANSFERENCIA, null, conta2);
        transferenciaTres = new Transferencia(3L, LocalDateTime.parse("2019-05-04 08:12:45+0300", formatter), new BigDecimal("-500.50"),
                Tipo.SAQUE, null, conta1);
        transferenciaQuatro = new Transferencia(4L, LocalDateTime.parse("2019-08-07 08:12:45+0300", formatter), new BigDecimal("-530.50"),
                Tipo.SAQUE, null, conta2);
        transferenciaCinco = new Transferencia(5L, LocalDateTime.parse("2020-06-08 10:15:01+0300", formatter), new BigDecimal("3241.23"),
                Tipo.TRANSFERENCIA, "Beltrano", conta1);
        transferenciaSeis = new Transferencia(6L, LocalDateTime.parse("2021-04-01 12:12:04+0300", formatter), new BigDecimal("25173.09"),
                Tipo.TRANSFERENCIA, "Ronnyscley", conta2);
    }

    void startConta() {
        conta1 = new Conta(1L, "Fulano");
        conta2 = new Conta(2L, "Sicrano");
    }

}
