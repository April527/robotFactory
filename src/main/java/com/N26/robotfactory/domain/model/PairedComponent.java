package com.N26.robotfactory.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PairedComponent {
    private String componentName;
    private String componentCode;
}
