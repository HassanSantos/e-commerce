package com.foursales.e_commerce.infrastructure.service;

import com.foursales.e_commerce.domain.service.OrderService;
import com.foursales.e_commerce.domain.service.model.Order;
import com.foursales.e_commerce.domain.service.model.Pedido;
import com.foursales.e_commerce.repository.OrderProductRepository;
import com.foursales.e_commerce.repository.OrderRepository;
import com.foursales.e_commerce.repository.ProductRepository;
import com.foursales.e_commerce.repository.entity.OrderEntity;
import com.foursales.e_commerce.repository.entity.OrderProductEntity;
import com.foursales.e_commerce.repository.entity.ProdutoEntity;
import com.foursales.e_commerce.repository.entity.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public record OrderServiceImpl(OrderRepository orderRepository,
                               OrderProductRepository orderProductRepository,
                               ProductRepository productRepository
) implements OrderService {

    @Override
    public Order createOrder(Order order) {
        OrderEntity orderEntity = OrderEntity.builder()
                .status("PENDENTE")
                .value(new BigDecimal("4500.00"))
                .creationDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .user(User.builder().id("7c97a0f8-f4ac-4942-940d-97d9e3cc2d7d").build()) // Associando o User ao Order
                .build();

        orderRepository.save(orderEntity);

        List<Pedido> pedidos = List.of(
                Pedido.builder().id("37c7688f-5c94-4ae1-bf94-e968692a4abe")
                        .quantidade(2)
                        .build(),
                Pedido.builder().id("9e3e411a-80d8-48b8-ab30-d7bcb569768d")
                        .quantidade(2)
                        .build(), Pedido.builder()
                        .id("b4550351-087d-494c-8d49-4a6c75fa524b")
                        .quantidade(2)
                        .build()
        );
        pedidos.forEach(i -> orderProductRepository.save(
                OrderProductEntity.builder().orderEntity(orderEntity)
                        .product(ProdutoEntity.builder().id(i.getId()).build())
                        .productQuantity(i.getQuantidade())
                        .build()));
        return null;
    }

    @Override
    public Order payOrder(String orderId) {

        var orderEntity = orderRepository.findById(orderId);
        var orderProduct = orderProductRepository.findByOrderEntity_Id(orderId);

        orderProduct.stream().filter(i ->
                        i.getProduct().getStockQuantity() - i.getProductQuantity() >= 0)
                .toList();

        orderProduct.forEach(i -> {
            i.getProduct().setStockQuantity(i.getProduct().getStockQuantity() - i.getProductQuantity());
            productRepository.save(i.getProduct());
        });

        orderEntity.ifPresent(order -> {
            order.setStatus("FINALIZADO");
            orderRepository.save(order);
        });

        return null;
    }

    @Override
    public List<Order> getOrdersByUser(UUID userId) {
        return List.of();
    }
}
