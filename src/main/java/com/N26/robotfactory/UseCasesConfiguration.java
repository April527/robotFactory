package com.N26.robotfactory;

import com.N26.robotfactory.domain.usecase.RobotUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class UseCasesConfiguration {

    @Bean
    public RobotUseCase robotUseCase(RobotFactory robotFactory) {
        return new RobotUseCase(robotFactory);
    }

}
