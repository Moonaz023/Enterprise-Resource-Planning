package com.erp.dto;

import java.sql.Date;
import java.util.List;

import com.erp.entity.PurchaseIngredientEntity;

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

public class IngredientBuyAndUseHistoryDTO {
	List<PurchaseIngredientEntity> PurchageHistory;
	
	List<IngredientUseHistoryDTO> useHistory;

}
