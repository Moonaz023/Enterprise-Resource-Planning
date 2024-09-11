package com.erp.dto;

import java.util.Map;
import java.util.AbstractMap.SimpleEntry;

import com.erp.entity.DistributorEntity;
//import com.erp.entity.ProductEntity;
//import com.erp.entity.UnitEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Builder
public class DistributorHistoryDTO {
	
	private DistributorEntity distributor;
	//private Map<SimpleEntry<ProductEntity, UnitEntity>, Integer> productSale;
	private Map<SimpleEntry<Long, Long>, Integer> productSale;
	private double totalPaid;
	private double totalDue;

}
