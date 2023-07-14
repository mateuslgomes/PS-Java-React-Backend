package br.com.banco.services;

import br.com.banco.dtos.response.ContaResponse;
import br.com.banco.model.Conta;
import br.com.banco.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContaServices {

    @Autowired
    ContaRepository contaRepository;

    public List<ContaResponse> findAll() {
        List<Conta> contas = contaRepository.findAll();

        return contas.stream()
                .map(ContaResponse::of)
                .collect(Collectors.toList());
    }

}
