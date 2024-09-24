package com.erp.entity;

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

public class SalesReportEntity {

	
	private long id;
	private String date;
	private double receptAmount;
	private double due;
	private double cost;

	
	
	private long tenantId;
	
}
