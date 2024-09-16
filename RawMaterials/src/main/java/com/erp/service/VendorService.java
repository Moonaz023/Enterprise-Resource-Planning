package com.erp.service;

import java.util.List;


import com.erp.dto.VendorHistoryDTO;
import com.erp.entity.VendorEntity;

public interface VendorService {

	void saveVendor(VendorEntity vendor,long tenantId);

	List<VendorEntity> getAllVendor(long tenantId);

	void deleteVendor(long id);

	void updateVendor(VendorEntity updatedVendor,long tenantId);

	VendorHistoryDTO vendorProfile(Long id);

}
