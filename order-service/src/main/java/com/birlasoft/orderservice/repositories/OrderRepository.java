package com.birlasoft.orderservice.repositories;

import com.birlasoft.domain.OrderData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderData, Long> {
}
