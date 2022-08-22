package com.solvd.fooddelivery.service;

public enum District {
    CENTRAL("central"),
    SOVETSKIY("sovetskiy"),
    PERVOMAYSKIY("pervomayskiy"),
    PARTIZANSKIY("partizanskiy"),
    ZAVODSKOY("zavodskoy"),
    LENINSKIY("leninskiy"),
    OKTIABRSKIY("oktiabrskiy"),
    MOSKOVSKIY("moskovskiy"),
    FRUNZENSKIY("frunzenskiy");

    private final String displayName;

    District(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
