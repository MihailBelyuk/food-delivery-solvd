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

    private final String name;

    District(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
