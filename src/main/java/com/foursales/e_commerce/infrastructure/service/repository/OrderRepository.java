package com.foursales.e_commerce.infrastructure.service.repository;

import com.foursales.e_commerce.dto.UserAverageTicketDTO;
import com.foursales.e_commerce.dto.UserOrderCountDTO;
import com.foursales.e_commerce.infrastructure.service.repository.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {

    @Query("""
        SELECT NEW com.foursales.e_commerce.dto.UserOrderCountDTO(
            u.name, COUNT(o.id))
        FROM User u
        INNER JOIN OrderEntity o ON u.id = o.userEntity.id
        GROUP BY u.id
        ORDER BY COUNT(o.id) DESC
        limit 5
        """)
    List<UserOrderCountDTO> findTop5UsersByOrderCount();

    @Query("""
            SELECT NEW com.foursales.e_commerce.dto.UserAverageTicketDTO(u.name,
                         CAST(AVG(o.value) as bigdecimal))
            FROM OrderEntity o
            INNER JOIN User u
                        on u.id = o.userEntity.id
            GROUP BY u.id, u.name
            ORDER BY AVG(o.value) DESC
            """)
    List<UserAverageTicketDTO> findUserAverageTicket();

    @Query("""
        SELECT SUM(p.value) 
        FROM OrderEntity p
        WHERE p.creationDate BETWEEN :startDate AND :endDate
        AND p.status = :status
        """)
    BigDecimal findTotalValueByDateRangeAndStatus(
            @Param("startDate") LocalDateTime dataInicial,
            @Param("endDate") LocalDateTime dataFinal,
            @Param("status") String status
    );

    List<OrderEntity> findByUser_Email(String email);
}
