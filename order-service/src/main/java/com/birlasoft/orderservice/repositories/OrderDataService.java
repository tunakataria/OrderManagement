package com.birlasoft.orderservice.repositories;

import com.birlasoft.domain.order.OrderData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDataService implements CrudRepository<OrderData, Long> {

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public <S extends OrderData> S save(S s) {
        return orderRepository.save(s);
    }

    @Override
    public <S extends OrderData> Iterable<S> saveAll(Iterable<S> iterable) {
        return orderRepository.saveAll(iterable);
    }

    @Override
    public Optional<OrderData> findById(Long aLong) {
        return orderRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return orderRepository.existsById(aLong);
    }

    @Override
    public Iterable<OrderData> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Iterable<OrderData> findAllById(Iterable<Long> iterable) {
        return orderRepository.findAllById(iterable);
    }

    @Override
    public long count() {
        return orderRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        orderRepository.deleteById(aLong);
    }

    @Override
    public void delete(OrderData orderData) {
        orderRepository.delete(orderData);
    }

    @Override
    public void deleteAll(Iterable<? extends OrderData> iterable) {
        orderRepository.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        orderRepository.deleteAll();
    }
}
