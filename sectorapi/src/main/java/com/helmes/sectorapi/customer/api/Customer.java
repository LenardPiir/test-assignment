package com.helmes.sectorapi.customer.api;

import java.io.Serializable;
import java.util.UUID;

public record Customer(UUID id, String name, boolean agreeToTerms) implements Serializable {
}
