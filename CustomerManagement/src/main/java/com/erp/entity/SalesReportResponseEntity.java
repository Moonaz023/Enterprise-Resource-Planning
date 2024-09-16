package com.erp.entity;

import java.util.List;

import com.erp.dto.ItemAndQuantityDTO;
//import com.erp.dto.RecipeDataDOT;
import com.erp.dto.ItemAndQuantityResponseDTO;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

public class SalesReportResponseEntity {

	
	private long id;
	private String date;
	private String details;
	private double receptAmount;
	private double due;
	private double cost;
	private long distributor_id;
	
	@ElementCollection
	@CollectionTable(joinColumns = @JoinColumn(name = "SalesId"))
	private List<ItemAndQuantityResponseDTO> itemAndQuantity;
	
	
	private long tenantId;
	
}
