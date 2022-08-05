package com.N26.robotfactory;

import com.N26.robotfactory.data.StockRepository;
import com.N26.robotfactory.domain.usecase.RobotUseCase;
import com.N26.robotfactory.domain.usecase.Stock;
import com.N26.robotfactory.gateway.IStock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class UseCasesConfiguration {

   @Bean
    public RobotUseCase robotUseCase(Stock stock) {
        return new RobotUseCase(stock);
    }

    @Bean
    public RobotFactory robotFactory1(StockRepository stockRepository){
        return new RobotFactory(stockRepository);
    }

    @Bean
    public Stock stock(RobotFactory robotFactory, IStock stock) {
        return new Stock(robotFactory, stock);
    }

}
