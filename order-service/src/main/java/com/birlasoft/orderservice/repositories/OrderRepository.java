package com.birlasoft.orderservice.repositories;

import com.birlasoft.domain.order.OrderData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderData, Long> {
}
