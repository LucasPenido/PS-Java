package br.com.supera.game.store.service;

import br.com.supera.game.store.dto.ProductsCartDTO;
import br.com.supera.game.store.entity.ProductsCart;
import br.com.supera.game.store.mapper.ProductCartMapper;
import br.com.supera.game.store.repository.ProductsCartRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductsCartService {

    private final ProductsCartRepository productsCartRepository;
    private final ProductCartMapper productCartMapper = ProductCartMapper.INSTANCE;

    public ProductsCart updateProductCart(ProductsCart productsCart) {
        return productsCartRepository.save(productsCart);
    }

    public void remove(ProductsCart productsCart) {
        productsCartRepository.delete(productsCart);
    }
}
