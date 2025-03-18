package com.foursales.e_commerce.infrastructure.service;

import com.foursales.e_commerce.mapper.OrderMapper;
import com.foursales.e_commerce.domain.service.OrderService;
import com.foursales.e_commerce.domain.service.model.Order;
import com.foursales.e_commerce.domain.service.model.OrderProduct;
import com.foursales.e_commerce.dto.UserAverageTicketDTO;
import com.foursales.e_commerce.dto.UserOrderCountDTO;
import com.foursales.e_commerce.infrastructure.service.repository.OrderProductRepository;
import com.foursales.e_commerce.infrastructure.service.repository.OrderRepository;
import com.foursales.e_commerce.infrastructure.service.repository.ProductRepository;
import com.foursales.e_commerce.infrastructure.service.repository.entity.OrderEntity;
import com.foursales.e_commerce.infrastructure.service.repository.entity.OrderProductEntity;
import com.foursales.e_commerce.infrastructure.service.repository.entity.ProdutoEntity;
import com.foursales.e_commerce.infrastructure.service.repository.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
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
        return orderMapper.orderEntityToOrder(orderEntity);
    }

    private BigDecimal getTotalValue(List<OrderProduct> orderProducts) {
        BigDecimal totalValue = BigDecimal.ZERO;
        for (OrderProduct orderProduct : orderProducts) {
            totalValue = totalValue.add(orderProduct.getValue().multiply(orderProduct.getValue()));
        }
        return totalValue;
    }

    @Override
    @Transactional
    public void payOrder(String orderId) {
        var orderEntity = findOrderById(orderId);
        var orderProducts = findOrderProductsByOrderId(orderId);

        if (isStockAvailable(orderProducts)) {
            updateProductStock(orderProducts);
            changeStatus(orderEntity, "FINALIZADO");
        } else {
            changeStatus(orderEntity, "CANCELADO");
        }

    }

    @Override
    public List<UserOrderCountDTO> getTop5Users() {
        try {

            return orderRepository.findTop5UsersByOrderCount();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserAverageTicketDTO> getAverageTiket() {
        try {
            return orderRepository.findUserAverageTicket();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BigDecimal totalAmountInvoicedPeriod(LocalDate startDate, LocalDate endDate) {
        return orderRepository.findTotalValueByDateRangeAndStatus(startDate.atStartOfDay(),
                endDate.atTime(23, 59, 59), "PENDENTE");
    }


    public List<OrderProduct> ordersByUser(String email) {
        try {
            var orders = orderRepository.findByUser_Email(email);
            return orderMapper.orderEntityToOrder(orders);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private OrderEntity findOrderById(String orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com ID: " + orderId));
    }

    private List<OrderProductEntity> findOrderProductsByOrderId(String orderId) {
        return orderProductRepository.findByOrderEntity_Id(orderId);
    }

    private boolean isStockAvailable(List<OrderProductEntity> orderProducts) {
        return orderProducts.stream()
                .allMatch(orderProduct -> orderProduct.getProduct().getStockQuantity() >= orderProduct.getProductQuantity());
    }

    private void updateProductStock(List<OrderProductEntity> orderProducts) {
        orderProducts.forEach(orderProduct -> {
            ProdutoEntity product = orderProduct.getProduct();
            product.setStockQuantity(product.getStockQuantity() - orderProduct.getProductQuantity());
            productRepository.save(product);
        });
    }

    private void changeStatus(OrderEntity orderEntity, String status) {
        orderEntity.setStatus(status);
        orderRepository.save(orderEntity);
    }
}
