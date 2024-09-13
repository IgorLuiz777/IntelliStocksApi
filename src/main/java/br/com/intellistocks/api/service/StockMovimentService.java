package br.com.intellistocks.api.service;

import br.com.intellistocks.api.models.stock.StockMoviment;
import br.com.intellistocks.api.repository.StockMovimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class StockMovimentService {

    @Autowired
    private StockMovimentRepository stockMovimentRepository;

    public StockMoviment createMoviment(StockMoviment stockMoviment) {
        return stockMovimentRepository.save(stockMoviment);
    }

    public Page<StockMoviment> listMoviment(Pageable pageable) {
        return stockMovimentRepository.findAll(pageable);
    }

    public Optional<StockMoviment> getMoviment(Long id) {
        if (!stockMovimentRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Moviment with id: " + id + " not found!");
        return stockMovimentRepository.findById(id);
    }

    public void deleteMoviment(Long id) {
        if (!stockMovimentRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Moviment with id: " + id + " not found!");
        stockMovimentRepository.deleteById(id);
    }

}
