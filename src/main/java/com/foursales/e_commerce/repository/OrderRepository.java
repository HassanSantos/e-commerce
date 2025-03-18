package com.foursales.e_commerce.repository;

import com.foursales.e_commerce.dto.UserOrderCountDTO;
import com.foursales.e_commerce.repository.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {

    @Query("""
        SELECT NEW com.foursales.e_commerce.dto.UserOrderCountDTO(
            u.name, COUNT(o.id))
        FROM User u
        INNER JOIN OrderEntity o ON u.id = o.user.id
        GROUP BY u.id
        ORDER BY COUNT(o.id) DESC
        limit 5
        """)
    List<UserOrderCountDTO> findTop5UsersByOrderCount();


}
