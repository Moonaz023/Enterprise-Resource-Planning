package com.erp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.erp.dto.DistributorHistoryDTO;
import com.erp.entity.DistributorEntity;
import com.erp.entity.SalesReportEntity;
import com.erp.repository.DistributorRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DistributorServiceImp.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class DistributorServiceImpDiffblueTest {
    @MockBean
    private DistributorRepository distributorRepository;

    @Autowired
    private DistributorServiceImp distributorServiceImp;

    @MockBean
    private SalesReportService salesReportService;

    /**
     * Method under test:
     * {@link DistributorServiceImp#saveDistributor(DistributorEntity)}
     */
    @Test
    void testSaveDistributor() {
        // Arrange
        DistributorEntity distributorEntity = new DistributorEntity();
        distributorEntity.setAddress("42 Main St");
        distributorEntity.setEmail("jane.doe@example.org");
        distributorEntity.setId(1L);
        distributorEntity.setName("Name");
        distributorEntity.setPhone("6625550144");
        distributorEntity.setTotal_order(1L);
        when(distributorRepository.save(Mockito.<DistributorEntity>any())).thenReturn(distributorEntity);

        DistributorEntity distributor = new DistributorEntity();
        distributor.setAddress("42 Main St");
        distributor.setEmail("jane.doe@example.org");
        distributor.setId(1L);
        distributor.setName("Name");
        distributor.setPhone("6625550144");
        distributor.setTotal_order(1L);

        // Act
        distributorServiceImp.saveDistributor(distributor);

        // Assert
        verify(distributorRepository).save(isA(DistributorEntity.class));
        assertEquals(0L, distributor.getTotal_order());
    }

    /**
     * Method under test:
     * {@link DistributorServiceImp#saveDistributor(DistributorEntity)}
     */
    @Test
    void testSaveDistributor2() {
        // Arrange
        when(distributorRepository.save(Mockito.<DistributorEntity>any()))
                .thenThrow(new EntityNotFoundException("An error occurred"));

        DistributorEntity distributor = new DistributorEntity();
        distributor.setAddress("42 Main St");
        distributor.setEmail("jane.doe@example.org");
        distributor.setId(1L);
        distributor.setName("Name");
        distributor.setPhone("6625550144");
        distributor.setTotal_order(1L);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> distributorServiceImp.saveDistributor(distributor));
        verify(distributorRepository).save(isA(DistributorEntity.class));
    }

    /**
     * Method under test: {@link DistributorServiceImp#getAlldistributor()}
     */
    @Test
    void testGetAlldistributor() {
        // Arrange
        ArrayList<DistributorEntity> distributorEntityList = new ArrayList<>();
        when(distributorRepository.findAll()).thenReturn(distributorEntityList);

        // Act
        List<DistributorEntity> actualAlldistributor = distributorServiceImp.getAlldistributor();

        // Assert
        verify(distributorRepository).findAll();
        assertTrue(actualAlldistributor.isEmpty());
        assertSame(distributorEntityList, actualAlldistributor);
    }

    /**
     * Method under test: {@link DistributorServiceImp#getAlldistributor()}
     */
    @Test
    void testGetAlldistributor2() {
        // Arrange
        when(distributorRepository.findAll()).thenThrow(new EntityNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> distributorServiceImp.getAlldistributor());
        verify(distributorRepository).findAll();
    }

    /**
     * Method under test: {@link DistributorServiceImp#deleteDistributor(long)}
     */
    @Test
    void testDeleteDistributor() {
        // Arrange
        doNothing().when(distributorRepository).deleteById(Mockito.<Long>any());

        // Act
        distributorServiceImp.deleteDistributor(1L);

        // Assert that nothing has changed
        verify(distributorRepository).deleteById(eq(1L));
    }

    /**
     * Method under test: {@link DistributorServiceImp#deleteDistributor(long)}
     */
    @Test
    void testDeleteDistributor2() {
        // Arrange
        doThrow(new EntityNotFoundException("An error occurred")).when(distributorRepository)
                .deleteById(Mockito.<Long>any());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> distributorServiceImp.deleteDistributor(1L));
        verify(distributorRepository).deleteById(eq(1L));
    }

    /**
     * Method under test:
     * {@link DistributorServiceImp#updateDistributor(DistributorEntity)}
     */
    @Test
    void testUpdateDistributor() {
        // Arrange
        DistributorEntity distributorEntity = new DistributorEntity();
        distributorEntity.setAddress("42 Main St");
        distributorEntity.setEmail("jane.doe@example.org");
        distributorEntity.setId(1L);
        distributorEntity.setName("Name");
        distributorEntity.setPhone("6625550144");
        distributorEntity.setTotal_order(1L);
        when(distributorRepository.save(Mockito.<DistributorEntity>any())).thenReturn(distributorEntity);

        DistributorEntity updatedDistributor = new DistributorEntity();
        updatedDistributor.setAddress("42 Main St");
        updatedDistributor.setEmail("jane.doe@example.org");
        updatedDistributor.setId(1L);
        updatedDistributor.setName("Name");
        updatedDistributor.setPhone("6625550144");
        updatedDistributor.setTotal_order(1L);

        // Act
        distributorServiceImp.updateDistributor(updatedDistributor);

        // Assert
        verify(distributorRepository).save(isA(DistributorEntity.class));
    }

    /**
     * Method under test:
     * {@link DistributorServiceImp#updateDistributor(DistributorEntity)}
     */
    @Test
    void testUpdateDistributor2() {
        // Arrange
        when(distributorRepository.save(Mockito.<DistributorEntity>any()))
                .thenThrow(new EntityNotFoundException("An error occurred"));

        DistributorEntity updatedDistributor = new DistributorEntity();
        updatedDistributor.setAddress("42 Main St");
        updatedDistributor.setEmail("jane.doe@example.org");
        updatedDistributor.setId(1L);
        updatedDistributor.setName("Name");
        updatedDistributor.setPhone("6625550144");
        updatedDistributor.setTotal_order(1L);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> distributorServiceImp.updateDistributor(updatedDistributor));
        verify(distributorRepository).save(isA(DistributorEntity.class));
    }

    /**
     * Method under test: {@link DistributorServiceImp#distributorProfile(Long)}
     */
    @Test
    void testDistributorProfile() {
        // Arrange
        DistributorEntity distributorEntity = new DistributorEntity();
        distributorEntity.setAddress("42 Main St");
        distributorEntity.setEmail("jane.doe@example.org");
        distributorEntity.setId(1L);
        distributorEntity.setName("Name");
        distributorEntity.setPhone("6625550144");
        distributorEntity.setTotal_order(1L);
        Optional<DistributorEntity> ofResult = Optional.of(distributorEntity);
        when(distributorRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(salesReportService.getSalesReportByDistributor(Mockito.<DistributorEntity>any()))
                .thenReturn(new ArrayList<>());

        // Act
        DistributorHistoryDTO actualDistributorProfileResult = distributorServiceImp.distributorProfile(1L);

        // Assert
        verify(salesReportService).getSalesReportByDistributor(isA(DistributorEntity.class));
        verify(distributorRepository).findById(eq(1L));
        assertEquals(0.0d, actualDistributorProfileResult.getTotalDue());
        assertEquals(0.0d, actualDistributorProfileResult.getTotalPaid());
        assertTrue(actualDistributorProfileResult.getProductSale().isEmpty());
        assertSame(distributorEntity, actualDistributorProfileResult.getDistributor());
    }

    /**
     * Method under test: {@link DistributorServiceImp#distributorProfile(Long)}
     */
    @Test
    void testDistributorProfile2() {
        // Arrange
        DistributorEntity distributorEntity = new DistributorEntity();
        distributorEntity.setAddress("42 Main St");
        distributorEntity.setEmail("jane.doe@example.org");
        distributorEntity.setId(1L);
        distributorEntity.setName("Name");
        distributorEntity.setPhone("6625550144");
        distributorEntity.setTotal_order(1L);
        Optional<DistributorEntity> ofResult = Optional.of(distributorEntity);
        when(distributorRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(salesReportService.getSalesReportByDistributor(Mockito.<DistributorEntity>any()))
                .thenThrow(new EntityNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> distributorServiceImp.distributorProfile(1L));
        verify(salesReportService).getSalesReportByDistributor(isA(DistributorEntity.class));
        verify(distributorRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link DistributorServiceImp#distributorProfile(Long)}
     */
    @Test
    void testDistributorProfile3() {
        // Arrange
        Optional<DistributorEntity> emptyResult = Optional.empty();
        when(distributorRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> distributorServiceImp.distributorProfile(1L));
        verify(distributorRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link DistributorServiceImp#distributorProfile(Long)}
     */
    @Test
    void testDistributorProfile4() {
        // Arrange
        DistributorEntity distributorEntity = new DistributorEntity();
        distributorEntity.setAddress("42 Main St");
        distributorEntity.setEmail("jane.doe@example.org");
        distributorEntity.setId(1L);
        distributorEntity.setName("Name");
        distributorEntity.setPhone("6625550144");
        distributorEntity.setTotal_order(1L);
        Optional<DistributorEntity> ofResult = Optional.of(distributorEntity);
        when(distributorRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        DistributorEntity distributor = new DistributorEntity();
        distributor.setAddress("42 Main St");
        distributor.setEmail("jane.doe@example.org");
        distributor.setId(1L);
        distributor.setName("Name");
        distributor.setPhone("6625550144");
        distributor.setTotal_order(1L);

        SalesReportEntity salesReportEntity = new SalesReportEntity();
        salesReportEntity.setDate("2020-03-01");
        salesReportEntity.setDetails("Details");
        salesReportEntity.setDistributor(distributor);
        salesReportEntity.setDue(10.0d);
        salesReportEntity.setId(1L);
        salesReportEntity.setItemAndQuantity(new ArrayList<>());
        salesReportEntity.setReceptAmount(10.0d);

        ArrayList<SalesReportEntity> salesReportEntityList = new ArrayList<>();
        salesReportEntityList.add(salesReportEntity);
        when(salesReportService.getSalesReportByDistributor(Mockito.<DistributorEntity>any()))
                .thenReturn(salesReportEntityList);

        // Act
        DistributorHistoryDTO actualDistributorProfileResult = distributorServiceImp.distributorProfile(1L);

        // Assert
        verify(salesReportService).getSalesReportByDistributor(isA(DistributorEntity.class));
        verify(distributorRepository).findById(eq(1L));
        assertEquals(10.0d, actualDistributorProfileResult.getTotalDue());
        assertEquals(10.0d, actualDistributorProfileResult.getTotalPaid());
        assertTrue(actualDistributorProfileResult.getProductSale().isEmpty());
        assertSame(distributorEntity, actualDistributorProfileResult.getDistributor());
    }
}
