package br.com.supera.game.store.service;

import br.com.supera.game.store.dto.ProductDTO;
import br.com.supera.game.store.entity.Product;
import br.com.supera.game.store.mapper.ProductMapper;
import br.com.supera.game.store.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    public List<ProductDTO> listAll() {
        List<Product> products = new ArrayList<>();
        products = productRepository.findAll();
        return products
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }
}
