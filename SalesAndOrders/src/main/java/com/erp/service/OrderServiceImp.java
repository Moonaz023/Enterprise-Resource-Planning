package com.erp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dto.CheckoutDataDTO;
import com.erp.dto.CheckoutPaymentDTO;
import com.erp.dto.CheckoutValidityResultDTO;
import com.erp.dto.DistributorEntityDTO;
import com.erp.dto.ItemAndQuantityDTO;
import com.erp.dto.ProductBatchesStockEntity;
import com.erp.dto.SellingUnitPriceResponseDTO;
import com.erp.entity.OrderEntity;
import com.erp.dto.ProductEntity;
import com.erp.entity.SalesReportEntity;
import com.erp.feignclient.DistributorsClient;
import com.erp.feignclient.ProductAndServicesClient;
import com.erp.repository.OrderRepository;
import com.erp.repository.SalesReportRepository;
/*
import com.erp.repository.ProductBatchesStockRepository;
import com.erp.repository.ProductRepository;
import com.erp.repository.StockRepository;
import com.erp.repository.UnitRepository;
import com.erp.repository.DistributorRepository;
import com.erp.entity.UnitEntity;
import com.erp.entity.ProductBatchesStockEntity;
import com.erp.entity.DistributorEntity;
import com.erp.entity.ProductBatchesStockEntity;
*/

import jakarta.transaction.Transactional;

import java.time.LocalDate;

@Service
public class OrderServiceImp implements OrderService {
	@Autowired   
	public OrderRepository orderRepository;
	@Autowired
	private SalesReportRepository salesReportRepository;
	/*
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StockRepository stockRepository;
	@Autowired
	private DistributorRepository distributorRepository;
	@Autowired
	private UnitRepository unitRepository;
	@Autowired
	private ProductBatchesStockRepository productBatchesStockRepository;
	*/
	
	@Autowired
	private ProductAndServicesClient productAndServicesClient;
	@Autowired
	private DistributorsClient distributorsClient;

	@Override
	public String addOrder(OrderEntity order,long tenantId,String token) {
		int i = 0;
		long[] products = order.getProduct();
		int[] quantity = order.getProductQuantity();
		long[] unit = order.getUnit();
		StringBuilder orderDetailsBuilder = new StringBuilder();
		for (long productId : products) {
			String productName = productAndServicesClient.findProductNameById(productId,token);
			String unitName = productAndServicesClient.findUnitNameById(unit[i],token);
			if (productName != null) {
				orderDetailsBuilder.append(productName).append(":" + quantity[i++]).append(" " + unitName).append(", ");
			}
		}
		String orderDetails = orderDetailsBuilder.toString();
		if (orderDetails.endsWith(", ")) {
			orderDetails = orderDetails.substring(0, orderDetails.length() - 2);
		}
		order.setOrderDetails(orderDetails);
		System.out.println("Order Details: " + orderDetails);

		order.setTenantId(tenantId);
		orderRepository.save(order);
		return "Order received successfully";

	}

	@Override
	public List<OrderEntity> getAllOrder(long tenantId) {

		return orderRepository.findByTenantId(tenantId);
	}

	@Override
	public CheckoutValidityResultDTO CheckOutValidityTest(long order_id,String token) {
		CheckoutValidityResultDTO checkoutValidityResult = new CheckoutValidityResultDTO();
		Optional<OrderEntity> optionalOrder = orderRepository.findById(order_id);
		double total = 0;
		double price;
		Optional<ProductEntity> optionalOrderedProduct;
		if (optionalOrder.isPresent()) {
			OrderEntity order = optionalOrder.get();
			long[] products = order.getProduct();
			int[] quantities = order.getProductQuantity();
			long[] unit = order.getUnit();
			int i = 0;
			List<CheckoutDataDTO> Checkoutdetails = new ArrayList<>();
			;
			for (long productId : products) {
				
				Integer stock = productAndServicesClient.findProductQuantityById(productId, unit[i],token);
				//System.out.println("Test Pass============>>>available stock>>>>"+stock);		
				//----->>>Integer stock = stockRepository.findProductQuantityById(productId, unit[i]);
				if (stock == null)
					stock = 0;
				if (stock - quantities[i] < 0) {
					checkoutValidityResult.setSuccess(false);
					checkoutValidityResult.setTotalPrice(0);
					checkoutValidityResult.setDetails(null);
					return checkoutValidityResult;
				}

				//optionalOrderedProduct = productAndServicesClient.findById(productId,token);
				//optionalOrderedProduct = productRepository.findById(productId);
				//ProductEntity orderedProduct = optionalOrderedProduct.get();
				ProductEntity orderedProduct = productAndServicesClient.findByIdd(productId,token);
				// price = orderedProduct.getPrice();
				List<SellingUnitPriceResponseDTO> unitWisePrice = orderedProduct.getUnitPrice();
				Map<Long, Double> unitPriceMap = unitWisePrice.stream()
						.collect(Collectors.toMap(it -> it.getUnit(), SellingUnitPriceResponseDTO::getPrice));

				price = unitPriceMap.get(unit[i]);
				total = total + (quantities[i] * price);
				CheckoutDataDTO checkoutData = new CheckoutDataDTO();
				checkoutData.setProductId(productId);
				checkoutData.setUnit(unit[i]);
				String unitName = productAndServicesClient.findUnitNameById(unit[i],token);
				checkoutData.setUnitName(unitName);
				checkoutData.setPrice(quantities[i] * price);
				checkoutData.setQuantity(quantities[i]);
				checkoutData.setProductName(orderedProduct.getName());
				Checkoutdetails.add(checkoutData);
				i++;
			}
			checkoutValidityResult.setSuccess(true);
			checkoutValidityResult.setTotalPrice(total);
			checkoutValidityResult.setDetails(Checkoutdetails);
			return checkoutValidityResult;
		} else {

			checkoutValidityResult.setSuccess(false);
			checkoutValidityResult.setTotalPrice(0);
			checkoutValidityResult.setDetails(null);
			return checkoutValidityResult;
		}
	}

	/*@Override
	public String checkoutNow(CheckoutPaymentDTO checkoutPayment, long tenantId, String token) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	@Transactional
	public String checkoutNow(CheckoutPaymentDTO checkoutPayment,long tenantId,String token) {
		double cost = 0;
		CheckoutValidityResultDTO validityDTO = CheckOutValidityTest(checkoutPayment.getOrderId(),token);
		boolean validity = validityDTO.isSuccess();
		if (validity == false) {
			return "not enough item";
		}
		SalesReportEntity salesReport = new SalesReportEntity();salesReport.setTenantId(tenantId);
		StringBuilder orderDetailsBuilder = new StringBuilder();
		List<CheckoutDataDTO> details = validityDTO.getDetails();
		List<ItemAndQuantityDTO> itemAndQuantityList = new ArrayList<>();
		;

		for (CheckoutDataDTO order : details) {

			//Integer stock = stockRepository.findProductQuantityById(order.getProductId(), order.getUnit());
			Integer stock = productAndServicesClient.findProductQuantityById(order.getProductId(), order.getUnit(),token);
			if (stock == null)
				stock = 0;

			stock = stock - order.getQuantity();
			//Optional<ProductEntity> Optional_product = productRepository.findById(order.getProductId());
			//ProductEntity product = Optional_product.get();
			ProductEntity product =productAndServicesClient.findByIdd(order.getProductId(),token);
			String unitName = productAndServicesClient.findUnitNameById(order.getUnit(),token);
			orderDetailsBuilder.append(product.getName()).append(":" + order.getQuantity()).append(" " + unitName)
					.append(", ");

			ItemAndQuantityDTO itemAndQuantity = new ItemAndQuantityDTO();
			itemAndQuantity.setProduct(order.getProductId());

			/*Optional<UnitEntity> unitEntityOpt = unitRepository.findById(order.getUnit());
			UnitEntity unitEntity = unitEntityOpt.get();*/

			// ============================================================================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.TEST

			itemAndQuantity.setUnit(order.getUnit());

			// ============================================================================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.TEST

			itemAndQuantity.setProductQuantity(order.getQuantity());
			itemAndQuantityList.add(itemAndQuantity);
			//stockRepository.updateProductQuantityById(product, order.getUnit(), stock);

			productAndServicesClient.updateProductQuantityById(order.getProductId(), order.getUnit(), stock,token);
			// Batch of Product

			/*List<ProductBatchesStockEntity> productBatchesStock = productBatchesStockRepository
					.findByProductAndProductionUnit(product, unitEntity);*/
			List<ProductBatchesStockEntity> productBatchesStock = productAndServicesClient
					.findByProductAndProductionUnit(order.getProductId(), order.getUnit() ,token);

			int remaing = order.getQuantity();

			for (ProductBatchesStockEntity it : productBatchesStock) {
				if (it.getQuantity() >= remaing) {
					it.setQuantity(it.getQuantity() - remaing);
					cost += it.getCostPerUnit() * remaing;
					remaing = 0;
				} else {
					remaing -= it.getQuantity();
					cost += it.getQuantity() * it.getCostPerUnit();
					it.setQuantity(0);
				}
				if (it.getQuantity() == 0) {
					//productBatchesStockRepository.delete(it);
					productAndServicesClient.deleteBatch(it.getId(),token);
				} else {
					//productBatchesStockRepository.save(it);
					productAndServicesClient.saveBatchUpdate(it.getId(),it.getQuantity(),token);
				}
				if (remaing == 0)
					break;

			}

		}
		salesReport.setItemAndQuantity(itemAndQuantityList);
		String orderDetails = orderDetailsBuilder.toString();
		if (orderDetails.endsWith(", ")) {
			orderDetails = orderDetails.substring(0, orderDetails.length() - 2);
		}
		salesReport.setCost(cost);
		//DistributorEntityDTO distributor = 
		long distributor_id=orderRepository.findDistributorByOrderId(checkoutPayment.getOrderId());
		
		DistributorEntityDTO distributor=distributorsClient.findDistributorByOrderId(distributor_id,token);
		
		distributor.setTotal_order(distributor.getTotal_order() + 1);
		//distributorRepository.save(distributor);
		distributorsClient.saveUpdate(distributor,token);
		
		salesReport.setDistributor_id(distributor.getId());
		salesReport.setDetails(orderDetails);
		salesReport.setReceptAmount(checkoutPayment.getReceptAmount());
		salesReport.setDue(validityDTO.getTotalPrice() - checkoutPayment.getReceptAmount());
		LocalDate today = LocalDate.now();
		StringBuilder todayStringBuilder = new StringBuilder();
		todayStringBuilder.append(today);
		String todayString = todayStringBuilder.toString();
		salesReport.setDate(todayString);

		salesReportRepository.save(salesReport);
		orderRepository.deleteById(checkoutPayment.getOrderId());

		return "Checkout successful";
	}
	

}