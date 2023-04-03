package com.project.gamelibrary.repository;


import com.project.gamelibrary.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order,Long> {
}
