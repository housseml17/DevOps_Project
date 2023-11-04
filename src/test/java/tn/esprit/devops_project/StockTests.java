package tn.esprit.devops_project;


import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.services.Iservices.IStockService;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@SpringBootTest
@Slf4j
@TestMethodOrder(OrderAnnotation.class)
public class StockTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    IStockService stockServices;

    Stock s = Stock.builder().title("test").build();

    @Test
    @Order(0)
    public void ajouterStockTest() {
        s = stockServices.addStock(s);
        log.info(s.toString());
        Assertions.assertNotNull(s.getIdStock());

    }

}
