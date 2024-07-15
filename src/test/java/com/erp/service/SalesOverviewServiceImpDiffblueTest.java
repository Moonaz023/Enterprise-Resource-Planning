package com.erp.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.erp.dto.SalesOverviewDTO;
import com.erp.repository.SalesReportRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SalesOverviewServiceImp.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class SalesOverviewServiceImpDiffblueTest {
    @Autowired
    private SalesOverviewServiceImp salesOverviewServiceImp;

    @MockBean
    private SalesReportRepository salesReportRepository;

    /**
     * Method under test: {@link SalesOverviewServiceImp#getSalesOverview()}
     */
    @Test
    void testGetSalesOverview() {
        // Arrange
        ArrayList<SalesOverviewDTO> salesOverviewDTOList = new ArrayList<>();
        when(salesReportRepository.getSalesOverview()).thenReturn(salesOverviewDTOList);

        // Act
        List<SalesOverviewDTO> actualSalesOverview = salesOverviewServiceImp.getSalesOverview();

        // Assert
        verify(salesReportRepository).getSalesOverview();
        assertTrue(actualSalesOverview.isEmpty());
        assertSame(salesOverviewDTOList, actualSalesOverview);
    }
}
