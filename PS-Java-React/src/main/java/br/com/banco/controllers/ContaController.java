package br.com.banco.controllers;

import br.com.banco.dtos.response.ContaResponse;
import br.com.banco.dtos.response.TransferenciaResponse;
import br.com.banco.services.ContaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("conta")
public class ContaController {

    @Autowired
    private ContaServices contaServices;

    @GetMapping("all")
    public List<ContaResponse> findAll() {
        return contaServices.findAll();
    }

}
