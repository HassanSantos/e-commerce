package com.foursales.e_commerce.infrastructure.service;

import com.foursales.e_commerce.domain.service.OrderService;
import com.foursales.e_commerce.domain.service.model.Order;
import com.foursales.e_commerce.domain.service.model.OrderProduct;
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
    public Order createOrder(List<OrderProduct> orderProducts, String user) {
        OrderEntity orderEntity = OrderEntity.builder()
                .status("PENDENTE")
                .value(getTotalValue(orderProducts))
                .creationDate(LocalDateTime.now())
                .user(User.builder().id(user).build()) // Associando o User ao Order
                .build();

        orderRepository.save(orderEntity);


        orderProducts.forEach(i -> orderProductRepository.save(
                OrderProductEntity.builder()
                        .orderEntity(orderEntity)
                        .product(ProdutoEntity.builder()
                                .id(i.getId()).build())
                        .productQuantity(i.getQuantidade())
                        .build()));
        return null;
    }

    private BigDecimal getTotalValue(List<OrderProduct> orderProducts) {
        BigDecimal totalValue = BigDecimal.ZERO;
        for (OrderProduct orderProduct : orderProducts) {
            totalValue = totalValue.add(orderProduct.getPreco().multiply(orderProduct.getPreco()));
        }
        return totalValue;
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
