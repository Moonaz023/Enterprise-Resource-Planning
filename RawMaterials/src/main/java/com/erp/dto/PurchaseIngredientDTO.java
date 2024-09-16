package com.erp.dto;

import java.sql.Date;
import java.util.List;

import com.erp.entity.IngredientBatchesStockEntity;
import com.erp.entity.IngredientEntity;
import com.erp.entity.PurchaseIngredientEntity;
import com.erp.entity.VendorEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@ToString
@Builder
public class PurchaseIngredientDTO {


	
    private Long id;

    private Date dateOfPurchase;

    
    private String ingredientName;  
    private double quantity;
    private String unitName;
    private double bill;
    
    
}
