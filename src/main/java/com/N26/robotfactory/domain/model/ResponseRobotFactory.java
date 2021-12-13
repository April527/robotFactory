package com.N26.robotfactory.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseRobotFactory {
    private String order_id;
    private Double total;
}
