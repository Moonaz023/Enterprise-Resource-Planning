package com.erp.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Entity
//@Table(name = "stock")
//@Table(name = "stock", uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "product_quantity"}))
@Table(name = "stock",
uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id", "product_quantity"})})

public class StockEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stock_id;
	@ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
	@Column(name = "product_quantity")
    private int productQuantity;
	@ManyToOne
	private UnitEntity productionUnit;
}
