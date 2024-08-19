package com.erp.service;

import java.util.List;

import com.erp.dto.DistributorHistoryDTO;
import com.erp.dto.VendorHistoryDTO;
import com.erp.entity.VendorEntity;

public interface VendorService {

	void saveVendor(VendorEntity vendor);

	List<VendorEntity> getAllVendor();

	void deleteVendor(long id);

	void updateVendor(VendorEntity updatedVendor);

	VendorHistoryDTO vendorProfile(Long id);

}
