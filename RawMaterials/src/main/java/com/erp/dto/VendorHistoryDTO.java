package com.erp.dto;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import com.erp.entity.VendorEntity;

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
public class VendorHistoryDTO {
	
	private VendorEntity vendor;
	private Map<SimpleEntry<String, String>, Double> productBuy;
	private double totalPaid;
	private double totalDue;

}

