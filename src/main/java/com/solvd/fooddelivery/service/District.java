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

    private final String minskDistrictName;

    District(String minskDistrictName) {
        this.minskDistrictName = minskDistrictName;
    }

    public String getMinskDistrictName() {
        return minskDistrictName;
    }
}
