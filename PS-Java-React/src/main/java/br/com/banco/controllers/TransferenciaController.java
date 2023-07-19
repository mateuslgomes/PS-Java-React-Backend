package br.com.banco.controllers;

import br.com.banco.dtos.response.TransferenciaResponse;
import br.com.banco.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("transferencias")
public class TransferenciaController {

    @Autowired
    TransferenciaService transferenciaService;

    @GetMapping("all")
    public ResponseEntity<List<TransferenciaResponse>> buscarTodos() {
        return ResponseEntity.ok(transferenciaService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<List<TransferenciaResponse>> BuscarTransferenciaPorIdConta(@PathVariable Long id) {
        return ResponseEntity.ok(transferenciaService.findById(id));
    }

    @GetMapping("find-periodo")
    public ResponseEntity<List<TransferenciaResponse>> buscarTransferenciasPorPeriodo(
            @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim) {
        return ResponseEntity.ok(transferenciaService.findByDataTransferenciaBetween(dataInicio, dataFim));
    }

    @GetMapping("find-operador")
    public ResponseEntity<List<TransferenciaResponse>> buscarPorNomeOperador(@RequestParam("operador") String nomeOperador) {
        return ResponseEntity.ok(transferenciaService.findByNomeOperadorTrasacao(nomeOperador));
    }

    @GetMapping("find-operador-data")
    public ResponseEntity<List<TransferenciaResponse>> buscarPorNomeOperadorData(
            @RequestParam("operador") String nomeOperador,
            @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim) {

        return ResponseEntity.ok(transferenciaService.findByNomeOperadorTrasacaoAndDataTransferenciaBetween
                (nomeOperador, dataInicio, dataFim));
    }

}
