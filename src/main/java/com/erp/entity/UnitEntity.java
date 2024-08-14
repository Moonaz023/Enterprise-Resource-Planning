package com.erp.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
//@ToString
@Builder
@Entity
public class UnitEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double cf;

	@OneToMany(mappedBy = "productionUnit", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<ProductionEntity> productionEntity;
	@OneToMany(mappedBy = "unit", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<IngredientEntity> ingredientEntity;
	
	@OneToMany(mappedBy = "productionUnit", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<ProductBatchesStockEntity> productBatchesStockEntity;
	

	@OneToMany(mappedBy = "productionUnit", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<StockEntity> stockEntity;

}