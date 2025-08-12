package com.deliverytech.delivery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.deliverytech.delivery.repository.*;

public class DataLoader implements CommandLineRunner {

    @Autowired
    private IRestaurantRepository restauranteRepository;

    @Autowired
    private IClienteRepository clientRespository;

    @Autowired
    private IProdutoRepository produtoRepository;

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("INICIANDO CARGA DE DADOS DE TESTE...");
    }

}
