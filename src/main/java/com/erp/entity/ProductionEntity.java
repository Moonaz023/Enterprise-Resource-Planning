package com.erp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

import com.erp.dto.RecipeDataDOT;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "production")
public class ProductionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of_production")
    private Date dateOfProduction;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
    @Column(name = "production_quantity")
    private int productionQuantity;
    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "production_id"))
    private List<RecipeDataDOT> recipe;
    //private double totalCost;
    
}
