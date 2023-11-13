package com.helmes.sectorapi.domain.customer.api;

import java.io.Serializable;
import java.util.UUID;

public record Customer(UUID id, String name, boolean agreeToTerms) implements Serializable {
}
