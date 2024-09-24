package com.erp.dto;

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
public class OperatingProfitDTO {
	
	private double GrossProfit;
	private double OperatingExpenses;
	private double OperatingProfit;
	
}
