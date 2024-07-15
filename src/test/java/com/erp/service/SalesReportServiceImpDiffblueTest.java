package com.erp.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.erp.entity.DistributorEntity;
import com.erp.entity.SalesReportEntity;
import com.erp.repository.SalesReportRepository;

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

@ContextConfiguration(classes = {SalesReportServiceImp.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class SalesReportServiceImpDiffblueTest {
    @MockBean
    private SalesReportRepository salesReportRepository;

    @Autowired
    private SalesReportServiceImp salesReportServiceImp;

    /**
     * Method under test: {@link SalesReportServiceImp#getAllSales()}
     */
    @Test
    void testGetAllSales() {
        // Arrange
        ArrayList<SalesReportEntity> salesReportEntityList = new ArrayList<>();
        when(salesReportRepository.findAll()).thenReturn(salesReportEntityList);

        // Act
        List<SalesReportEntity> actualAllSales = salesReportServiceImp.getAllSales();

        // Assert
        verify(salesReportRepository).findAll();
        assertTrue(actualAllSales.isEmpty());
        assertSame(salesReportEntityList, actualAllSales);
    }

    /**
     * Method under test:
     * {@link SalesReportServiceImp#getSalesReportByDistributor(DistributorEntity)}
     */
    @Test
    void testGetSalesReportByDistributor() {
        // Arrange
        ArrayList<SalesReportEntity> salesReportEntityList = new ArrayList<>();
        when(salesReportRepository.findByDistributor(Mockito.<DistributorEntity>any())).thenReturn(salesReportEntityList);

        DistributorEntity distributor = new DistributorEntity();
        distributor.setAddress("42 Main St");
        distributor.setEmail("jane.doe@example.org");
        distributor.setId(1L);
        distributor.setName("Name");
        distributor.setPhone("6625550144");
        distributor.setTotal_order(1L);

        // Act
        List<SalesReportEntity> actualSalesReportByDistributor = salesReportServiceImp
                .getSalesReportByDistributor(distributor);

        // Assert
        verify(salesReportRepository).findByDistributor(isA(DistributorEntity.class));
        assertTrue(actualSalesReportByDistributor.isEmpty());
        assertSame(salesReportEntityList, actualSalesReportByDistributor);
    }
}
