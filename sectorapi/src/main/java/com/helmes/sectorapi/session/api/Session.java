package com.helmes.sectorapi.session.api;

import java.io.Serializable;
import java.util.UUID;

public record Session(UUID id) implements Serializable {
}
