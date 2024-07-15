package com.erp.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.erp.entity.VendorEntity;
import com.erp.repository.VendorRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {VendorServiceImp.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class VendorServiceImpDiffblueTest {
    @MockBean
    private VendorRepository vendorRepository;

    @Autowired
    private VendorServiceImp vendorServiceImp;

    /**
     * Method under test: {@link VendorServiceImp#saveVendor(VendorEntity)}
     */
    @Test
    void testSaveVendor() {
        // Arrange
        VendorEntity vendorEntity = new VendorEntity();
        vendorEntity.setAddress("42 Main St");
        vendorEntity.setEmail("jane.doe@example.org");
        vendorEntity.setId(1L);
        vendorEntity.setName("Name");
        vendorEntity.setPhone("6625550144");
        when(vendorRepository.save(Mockito.<VendorEntity>any())).thenReturn(vendorEntity);

        VendorEntity vendor = new VendorEntity();
        vendor.setAddress("42 Main St");
        vendor.setEmail("jane.doe@example.org");
        vendor.setId(1L);
        vendor.setName("Name");
        vendor.setPhone("6625550144");

        // Act
        vendorServiceImp.saveVendor(vendor);

        // Assert that nothing has changed
        verify(vendorRepository).save(isA(VendorEntity.class));
    }

    /**
     * Method under test: {@link VendorServiceImp#getAllVendor()}
     */
    @Test
    void testGetAllVendor() {
        // Arrange
        ArrayList<VendorEntity> vendorEntityList = new ArrayList<>();
        when(vendorRepository.findAll()).thenReturn(vendorEntityList);

        // Act
        List<VendorEntity> actualAllVendor = vendorServiceImp.getAllVendor();

        // Assert
        verify(vendorRepository).findAll();
        assertTrue(actualAllVendor.isEmpty());
        assertSame(vendorEntityList, actualAllVendor);
    }

    /**
     * Method under test: {@link VendorServiceImp#deleteVendor(long)}
     */
    @Test
    void testDeleteVendor() {
        // Arrange
        doNothing().when(vendorRepository).deleteById(Mockito.<Long>any());

        // Act
        vendorServiceImp.deleteVendor(1L);

        // Assert that nothing has changed
        verify(vendorRepository).deleteById(eq(1L));
    }

    /**
     * Method under test: {@link VendorServiceImp#updateVendor(VendorEntity)}
     */
    @Test
    void testUpdateVendor() {
        // Arrange
        VendorEntity vendorEntity = new VendorEntity();
        vendorEntity.setAddress("42 Main St");
        vendorEntity.setEmail("jane.doe@example.org");
        vendorEntity.setId(1L);
        vendorEntity.setName("Name");
        vendorEntity.setPhone("6625550144");
        when(vendorRepository.save(Mockito.<VendorEntity>any())).thenReturn(vendorEntity);

        VendorEntity updatedVendor = new VendorEntity();
        updatedVendor.setAddress("42 Main St");
        updatedVendor.setEmail("jane.doe@example.org");
        updatedVendor.setId(1L);
        updatedVendor.setName("Name");
        updatedVendor.setPhone("6625550144");

        // Act
        vendorServiceImp.updateVendor(updatedVendor);

        // Assert that nothing has changed
        verify(vendorRepository).save(isA(VendorEntity.class));
    }
}
