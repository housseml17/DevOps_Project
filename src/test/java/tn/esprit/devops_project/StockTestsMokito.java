package tn.esprit.devops_project;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import tn.esprit.devops_project.controllers.StockController;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.Iservices.IStockService;

@Slf4j
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class StockTestsMokito {

    @Mock
    StockRepository stockRepository;

    @InjectMocks
    IStockService stockService;

    Stock s = Stock.builder().title("testt").build();

    List<Stock> list= new ArrayList<Stock>() {
        {
            add(Stock.builder().title("testtt").build());
            add(Stock.builder().title("testttt").build());
        }
    };

    @Test
    public void addStockTest() {
        Mockito.when(stockRepository.save(Mockito.any(Stock.class))).then(invocation -> {
            Stock model = invocation.getArgument(0, Stock.class);
            model.setIdStock(1L);
            return model;
        });
        log.info("Avant ==> " + s.toString());
        Stock stock = stockService.addStock(s);
        assertSame(stock, s);
        log.info("Après ==> " + s.toString());
    }

    @Test
    public void retreiveStockTest() {
        Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(s));
        Stock stock = stockService.retrieveStock(1L);
        assertNotNull(stock);
        log.info("get ==> " + stock.toString());
        verify(stockRepository).findById(Mockito.anyLong());

    }

}
