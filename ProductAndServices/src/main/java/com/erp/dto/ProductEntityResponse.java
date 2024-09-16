package com.erp.dto;

import java.util.List;

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

public class ProductEntityResponse {

	private long id;
	private String productCode;
	private String name;
	private String category;
	private List<SellingUnitPriceResponseDTO> unitPrice;

	private long tenantId;

}
