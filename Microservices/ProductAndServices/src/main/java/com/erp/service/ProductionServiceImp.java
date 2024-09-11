package com.erp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import com.erp.entity.IngredientEntity;
import com.erp.dto.RecipeDataDTO;
import com.erp.dto.IngredientStocKResponseDTO;
//import com.erp.entity.IngredientBatchesStockEntity;
import com.erp.entity.ProductBatchesStockEntity;
//import com.erp.entity.ProductBatchesStockEntity;
import com.erp.entity.ProductEntity;
import com.erp.entity.ProductionEntity;
import com.erp.feignclient.RowMetaralServiceClient;
import com.erp.repository.ProductBatchesStockRepository;
//import com.erp.repository.ProductBatchesStockRepository;
import com.erp.repository.ProductionRepository;
import java.lang.String;

import jakarta.transaction.Transactional;

@Service
public class ProductionServiceImp implements ProductionService {

	@Autowired
	private ProductionRepository productionRepository;
	@Autowired
	private StockService stockService;
	/*
	 * @Autowired private IngredientStockService ingredientStockService;
	 */
	@Autowired
	private ProductBatchesStockRepository productBatchesStockRepository;
	/*
	 * @Autowired private LoadBalancerClient loadBalancerClient;
	 */

	@Autowired
	private RowMetaralServiceClient rowMetaralServiceClient;

	@Override
	@Transactional
	public String saveProduction(ProductionEntity production, long tenantId, String token) {

		IngredientEntity ingredientEntity = rowMetaralServiceClient.getIngredientsById(1L,token);
		System.out.println(ingredientEntity);

		double totalCost = 0;
		List<RecipeDataDTO> recipeDatas = production.getRecipe();

		/*
		 * testing
		 * 
		 * if(rowMetaralServiceClient.checkAvailablity(recipeDatas))
		 * System.out.println("_________________>Yessssssssss");
		 * 
		 * testing End
		 */
		IngredientStocKResponseDTO ingredientStocKResponse = rowMetaralServiceClient
				.updateStockByProduction(recipeDatas,token);

		if (ingredientStocKResponse.isAvailability()) {
			try {
				/*
				 * for (RecipeDataDOT recipeData : recipeDatas) { totalCost +=
				 * ingredientStockService.modifystock_purchagedlt(recipeData.getIngredient(),
				 * recipeData.getIngredientQuantity()); }
				 */
				totalCost += ingredientStocKResponse.getTotalCost();
				// production.setMargin(production.getProduct().getPrice()-(totalCost/production.getProductionQuantity()));
				production.setUnitCost(totalCost / production.getProductionQuantity());
				production.setTenantId(tenantId);
				ProductionEntity savedProduction = productionRepository.save(production);

				stockService.updateStock(savedProduction.getProduct(), savedProduction.getProductionQuantity(),
						savedProduction.getProductionUnit());
				System.out.println("Total Cost=" + totalCost);

				/* Batch of Product */

				ProductBatchesStockEntity productBatchesStock = new ProductBatchesStockEntity();
				productBatchesStock.setCostPerUnit(totalCost / production.getProductionQuantity());
				productBatchesStock.setProduct(production.getProduct());
				productBatchesStock.setProductionUnit(savedProduction.getProductionUnit());
				productBatchesStock.setReferenceKey("Production-" + String.valueOf(savedProduction.getId()));
				productBatchesStock.setQuantity(savedProduction.getProductionQuantity());

				productBatchesStock.setProductionUnit(production.getProductionUnit());
				productBatchesStock.setTenantId(tenantId);
				productBatchesStockRepository.save(productBatchesStock);

				return "ok";
			} catch (Exception e) {
				throw e;
			}
		} else {
			System.out.println("Not enough ingrediant");
			return "no";
		}

	}

	@Override
	public List<ProductionEntity> getAllproduction(long tenantId) {
		return productionRepository.findByTenantId(tenantId);
	}

	@Override
	public ProductionEntity getProductionById(long id) {
		Optional<ProductionEntity> productOptional = productionRepository.findById(id);
		return productOptional.orElse(null);
	}

	@Override // need new test 1
	public void updateProduction(ProductionEntity updatedProduction) {
		Optional<ProductionEntity> oldProductionOptional = productionRepository.findById(updatedProduction.getId());

		if (oldProductionOptional.isPresent()) {
			ProductionEntity oldProduction = oldProductionOptional.get();

			int oldQuantity = oldProduction.getProductionQuantity();
			int newQuantity = updatedProduction.getProductionQuantity();

			ProductEntity oldProduct = oldProduction.getProduct();
			ProductEntity newProduct = updatedProduction.getProduct();

			updatedProduction.setTenantId(oldProduction.getTenantId());

			ProductionEntity savedProduction = productionRepository.save(updatedProduction);
			ProductBatchesStockEntity batch = productBatchesStockRepository
					.findByReferenceKey("Production-" + String.valueOf(savedProduction.getId()));
			batch.setQuantity(newQuantity);
			batch.setProduct(newProduct);
			batch.setProductionUnit(updatedProduction.getProductionUnit());
			batch.setCostPerUnit(updatedProduction.getUnitCost());
			productBatchesStockRepository.save(batch);

			if (oldProduct.equals(newProduct)
					&& oldProduction.getProductionUnit().equals(updatedProduction.getProductionUnit())
					&& oldQuantity != newQuantity) {

				stockService.updateStockQuantity(savedProduction.getProduct(), savedProduction.getProductionUnit(),
						newQuantity, oldQuantity);
			} else if (!oldProduct.equals(newProduct)
					|| !oldProduction.getProductionUnit().equals(updatedProduction.getProductionUnit())) {
				stockService.updateStockWhenProductChanged(oldProduct, oldProduction.getProductionUnit(), newProduct,
						savedProduction.getProductionUnit(), newQuantity, oldQuantity);
			}
		} else {

		}
		// Edit connection with ingredient also
	}

	@Override
	public void deleteProduction(long id) {
		Optional<ProductionEntity> Production = productionRepository.findById(id);
		if (Production.isPresent()) {
			ProductionEntity production = Production.get();

			ProductEntity Product = production.getProduct();
			int Quantity = production.getProductionQuantity();
			// work--------------------
			// work--------------------
			// work--------------------
			// work--------------------
			// work--------------------
			// work--------------------
			// work--------------------

			// stockService.updateStockWhenProductionDeteted(Product, Quantity);

			// Delete connection with ingredient also
		}

		productionRepository.deleteById(id);

	}

}
