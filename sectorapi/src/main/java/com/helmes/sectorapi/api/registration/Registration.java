package com.helmes.sectorapi.api.registration;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Registration implements Serializable {
    private String name;
    private List<String> sectors;
    private boolean agreeToTerms;
}
