package com.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
