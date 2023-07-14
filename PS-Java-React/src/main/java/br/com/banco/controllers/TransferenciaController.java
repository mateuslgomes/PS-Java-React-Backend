package br.com.banco.controllers;

import br.com.banco.dtos.response.ContaResponse;
import br.com.banco.dtos.response.TransferenciaResponse;
import br.com.banco.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("transferencia")
public class TransferenciaController {

    @Autowired
    TransferenciaService transferenciaService;

    @GetMapping("all")
    public List<TransferenciaResponse> findAll() {
        return transferenciaService.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<List<TransferenciaResponse>> findTransferenciaByIdConta(@PathVariable Long id) {
        return ResponseEntity.ok(transferenciaService.findById(id));
    }

}
