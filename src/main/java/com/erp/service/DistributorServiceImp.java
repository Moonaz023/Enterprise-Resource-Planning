package com.erp.service;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dto.DistributorHistoryDTO;
import com.erp.dto.ItemAndQuantityDTO;
import com.erp.entity.DistributorEntity;
import com.erp.entity.ProductEntity;
import com.erp.entity.SalesReportEntity;
import com.erp.entity.UnitEntity;
import com.erp.repository.DistributorRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class DistributorServiceImp implements DistributorService {
	@Autowired
	private DistributorRepository distributorRepository;

	@Autowired
	private SalesReportService salesReportService;

	@Override
	public void saveDistributor(DistributorEntity distributor) {
		distributor.setTotal_order(0);
		distributorRepository.save(distributor);

	}

	@Override
	public List<DistributorEntity> getAlldistributor() {
		return distributorRepository.findAll();
	}

	@Override
	public void deleteDistributor(long id) {
		distributorRepository.deleteById(id);

	}

	@Override
	public void updateDistributor(DistributorEntity updatedDistributor) {
		updatedDistributor.setTotal_order(updatedDistributor.getTotal_order());
		distributorRepository.save(updatedDistributor);

	}

	@Override
	public DistributorHistoryDTO distributorProfile(Long id) {
        Optional<DistributorEntity> distributorOptional = distributorRepository.findById(id);
        if (distributorOptional.isEmpty()) {
            throw new EntityNotFoundException("Distributor not found for ID: " + id);
        }

        DistributorEntity distributor = distributorOptional.get();
        List<SalesReportEntity> salesReport = salesReportService.getSalesReportByDistributor(distributor);

        Map<SimpleEntry<ProductEntity, UnitEntity>, Integer> productSale = new HashMap<>();
        double totalPaid = 0;
        double totalDue = 0;

        for (SalesReportEntity report : salesReport) {
            totalPaid += report.getReceptAmount();
            totalDue += report.getDue();
            List<ItemAndQuantityDTO> itemAndQuantityList = report.getItemAndQuantity();

            if (itemAndQuantityList == null) {
                continue;
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
    }

}
