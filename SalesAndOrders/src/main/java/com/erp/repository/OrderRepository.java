package com.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.erp.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

	/*@Query("SELECT o.distributor_id FROM OrderEntity o WHERE o.id = :orderId")
    DistributorEntity findDistributorByOrderId(@Param("orderId") long orderId);*/

	List<OrderEntity> findByTenantId(long tenantId);
	@Query("SELECT o.distributor_id FROM OrderEntity o WHERE o.id = :orderId")
	long findDistributorByOrderId(long orderId);

}
