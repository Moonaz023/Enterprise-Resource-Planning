package com.erp.dto;




import com.erp.entity.ProductEntity;
import com.erp.entity.UnitEntity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ItemAndQuantityResponseDTO {

	//@ManyToOne
	private long product;
	//@ManyToOne
	private long unit;
	private int  productQuantity;
}
