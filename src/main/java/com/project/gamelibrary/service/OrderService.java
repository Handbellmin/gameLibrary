package com.project.gamelibrary.service;

import com.project.gamelibrary.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class OrderService {
    private OrderRepository orderRepository;

    public void addOrder() {
        //주문, 주문상품, 배달지 선정
    }
}
