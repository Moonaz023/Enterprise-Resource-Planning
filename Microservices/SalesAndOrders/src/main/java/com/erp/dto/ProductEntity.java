package com.erp.dto;

import java.util.List;

import com.erp.dto.SellingUnitPriceResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

public class ProductEntity {

	/*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)*/
	private long id;
	private String productCode;
	private String name;
	private String category;
	/*@ElementCollection
	@CollectionTable(joinColumns = @JoinColumn(name = "unitPrice"))*/
	private List<SellingUnitPriceResponseDTO> unitPrice;
	
	
	private long tenantId;

}
