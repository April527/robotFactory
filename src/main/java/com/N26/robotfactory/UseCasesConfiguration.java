package com.N26.robotfactory;

import com.N26.robotfactory.domain.usecase.RobotUseCase;
import com.N26.robotfactory.gateway.IStock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class UseCasesConfiguration {

    @Bean
    public RobotUseCase robotUseCase(RobotFactory robotFactory, IStock stockRepository) {
        return new RobotUseCase(robotFactory, stockRepository);
    }

}
