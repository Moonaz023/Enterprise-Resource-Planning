package com.erp.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dto.DistributorHistoryDTO;
import com.erp.dto.ItemAndQuantityDTO;
import com.erp.dto.ItemAndQuantityResponseDTO;
import com.erp.entity.DistributorEntity;
import com.erp.entity.ProductEntity;
import com.erp.entity.SalesReportEntity;
import com.erp.entity.UnitEntity;
import com.erp.feignclient.ProductAndServicesClient;
import com.erp.feignclient.SalesReportClient;
import com.erp.entity.SalesReportResponseEntity;
/*import com.erp.entity.SalesReportEntity;
import com.erp.entity.UnitEntity;*/
import com.erp.repository.DistributorRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;



@Service
public class DistributorServiceImp implements DistributorService {
	@Autowired
	private DistributorRepository distributorRepository;
	
	@Autowired
	private SalesReportClient salesReportClient;
	@Autowired
	private ProductAndServicesClient productAndServicesClient;

	@Override
	public void saveDistributor(DistributorEntity distributor,long tenantId) {
		distributor.setTotal_order(0);
		distributor.setTenantId(tenantId);
		distributorRepository.save(distributor);

	}

	@Override
	public List<DistributorEntity> getAlldistributor(long tenantId) {
		return distributorRepository.findByTenantId(tenantId);
	}

	@Override
	public void deleteDistributor(long id) {
		distributorRepository.deleteById(id);

	}

	@Override
	public void updateDistributor(DistributorEntity updatedDistributor,long tenantId) {
		updatedDistributor.setTotal_order(updatedDistributor.getTotal_order());
		updatedDistributor.setTenantId(tenantId);
		distributorRepository.save(updatedDistributor);

	}

	@Override
	public DistributorHistoryDTO distributorProfile(Long id,String token) {
        Optional<DistributorEntity> distributorOptional = distributorRepository.findById(id);
        if (distributorOptional.isEmpty()) {
            throw new EntityNotFoundException("Distributor not found for ID: " + id);
        }

        DistributorEntity distributor = distributorOptional.get();
        
        //List<SalesReportEntity> salesReport = salesReportService.getSalesReportByDistributor(distributor);
        
        List<SalesReportResponseEntity> salesReport =salesReportClient.getSalesReportByDistributor(distributor.getId(),token);

        Map<SimpleEntry<ProductEntity, UnitEntity>, Integer> productSale = new HashMap<>();
        double totalPaid = 0;
        double totalDue = 0;

        for (SalesReportResponseEntity report : salesReport) {
            totalPaid += report.getReceptAmount();
            totalDue += report.getDue();
            List<ItemAndQuantityResponseDTO> itemAndQuantityResponseList = report.getItemAndQuantity();

            if (itemAndQuantityResponseList == null) {
                continue;
            }
            List<ItemAndQuantityDTO> itemAndQuantityList =new ArrayList<>();
            
            for(ItemAndQuantityResponseDTO it : itemAndQuantityResponseList) {
            	ItemAndQuantityDTO itemAndQuantityDTO=new ItemAndQuantityDTO();
            	itemAndQuantityDTO.setProduct(productAndServicesClient.getProductById(it.getProduct()));
            	itemAndQuantityDTO.setUnit(productAndServicesClient.getUnitById(it.getUnit()));
            	itemAndQuantityDTO.setProductQuantity(it.getProductQuantity());
            	
            	itemAndQuantityList.add(itemAndQuantityDTO);
            }
            

            for (ItemAndQuantityDTO item : itemAndQuantityList) {
                SimpleEntry<ProductEntity, UnitEntity> key = new SimpleEntry<>(item.getProduct(), item.getUnit());
                productSale.put(key, productSale.getOrDefault(key, 0) + item.getProductQuantity());
            }
        }

        DistributorHistoryDTO distributorHistory = new DistributorHistoryDTO();
        distributorHistory.setProductSale(productSale);
        distributorHistory.setTotalDue(totalDue);
        distributorHistory.setTotalPaid(totalPaid);
        distributorHistory.setDistributor(distributor);

        return distributorHistory;
		//return null;
    }

}
