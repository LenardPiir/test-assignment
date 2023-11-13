package com.helmes.sectorapi.domain.session.api;

import java.io.Serializable;
import java.util.UUID;

public record Session(UUID id) implements Serializable {
}
