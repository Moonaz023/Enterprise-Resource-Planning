package com.erp.dto;

import java.sql.Date;

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
public class IngredientUseHistoryDTO {

	private String ingrediant;
	private Date dateOfUse;
	private double ingredientQuantity;
}
