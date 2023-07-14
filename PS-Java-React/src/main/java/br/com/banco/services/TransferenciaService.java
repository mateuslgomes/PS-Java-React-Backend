package br.com.banco.services;

import br.com.banco.dtos.response.TransferenciaResponse;
import br.com.banco.model.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferenciaService {

    @Autowired
    TransferenciaRepository transferenciaRepository;

    public List<TransferenciaResponse> findAll() {
        List<Transferencia> transferencias = transferenciaRepository.findAll();

        return transferencias.stream()
                .map(TransferenciaResponse::of)
                .collect(Collectors.toList());
    }

}
