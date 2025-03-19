package com.foursales.e_commerce.infrastructure.service;

import com.foursales.e_commerce.exeption.OrderServiceException;
import com.foursales.e_commerce.infrastructure.service.repository.entity.UserEntity;
import com.foursales.e_commerce.mapper.OrderMapper;
import com.foursales.e_commerce.domain.service.OrderService;
import com.foursales.e_commerce.dto.OrderDto;
import com.foursales.e_commerce.dto.OrderProductDto;
import com.foursales.e_commerce.dto.UserAverageTicketDTO;
import com.foursales.e_commerce.dto.UserOrderCountDTO;
import com.foursales.e_commerce.infrastructure.service.repository.OrderProductRepository;
import com.foursales.e_commerce.infrastructure.service.repository.OrderRepository;
import com.foursales.e_commerce.infrastructure.service.repository.ProductRepository;
import com.foursales.e_commerce.infrastructure.service.repository.entity.OrderEntity;
import com.foursales.e_commerce.infrastructure.service.repository.entity.OrderProductEntity;
import com.foursales.e_commerce.infrastructure.service.repository.entity.ProdutoEntity;
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
    public OrderDto createOrder(List<OrderProductDto> orderProductDtos, String user) {

        OrderEntity orderEntity = OrderEntity.builder()
                .status("PENDENTE")
                .value(getTotalValue(orderProductDtos))
                .creationDate(LocalDateTime.now())
                .userEntity(UserEntity.builder().id(user).build()) // Associando o User ao Order
                .build();

        //TODO: Criar enum do status
        orderRepository.save(orderEntity);

        orderProductDtos.forEach(i -> orderProductRepository.save(
                OrderProductEntity.builder()
                        .orderEntity(orderEntity)
                        .product(ProdutoEntity.builder()
                                .id(i.getId()).build())
                        .productQuantity(i.getQuantidade())
                        .build()));
        return orderMapper.orderEntityToOrder(orderEntity);
    }

    private BigDecimal getTotalValue(List<OrderProductDto> orderProductDtos) {
        BigDecimal totalValue = BigDecimal.ZERO;
        for (OrderProductDto orderProductDto : orderProductDtos) {
            totalValue = totalValue.add(orderProductDto.getValue().multiply(orderProductDto.getValue()));
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
            //TODO: Criar constante com as mensagens
            throw new OrderServiceException("A compra foi cancelada, não há produtos suficientes", 500);
        }

    }

    @Override
    public List<UserOrderCountDTO> getTop5Users() {
        try {

            return orderRepository.findTop5UsersByOrderCount();
        } catch (Exception e) {
            throw new OrderServiceException("Ocorreu um erro ao listar os 5 usuários que mais compram", e, 500);
        }
    }

    @Override
    public List<UserAverageTicketDTO> getAverageTiket() {
        try {
            return orderRepository.findUserAverageTicket();

        } catch (RuntimeException e) {
            throw new OrderServiceException("Ocorreu um erro ao listar o tiket médio de cada usuário", e, 500);
        }
    }

    @Override
    public BigDecimal totalAmountInvoicedPeriod(LocalDate startDate, LocalDate endDate) {
        try {
            return orderRepository.findTotalValueByDateRangeAndStatus(startDate.atStartOfDay(),
                    endDate.atTime(23, 59, 59), "FINALIZADO");
        }catch (Exception e){
            throw new OrderServiceException("Ocorreu um erro ao retornar faturamento", e, 500);

        }
    }


    public List<OrderProductDto> ordersByUser(String email) {
        try {
            var orders = orderRepository.findByUserEntity_Email(email);
            return orderMapper.orderEntityToOrder(orders);
        } catch (Exception e) {
            throw new OrderServiceException("Ocorreu um erro ao consultar pedidos do usuário", e, 500);
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
