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
public class CheckoutValidityResultDOT {

	private boolean success;
	private List<Double> prices;
    private double totalPrice;
    private List<CheckoutData> details;
}
