package org.example;

import java.util.Objects;

public enum TariffType {
    UNLIMITED("06"),
    PER_MINUTE("03"),
    REGULAR("11");

    private final String tariffTypeName;


    TariffType(String tariffTypeName) {
        this.tariffTypeName = tariffTypeName;
    }


    public static TariffType getTypeFromString(String type) {
        for (TariffType tariffType : TariffType.values()) {
            if (tariffType.getTariffTypeName().equals(type)) {
                return tariffType;
            }
        }
       throw new IllegalArgumentException("No TariffType found with type: " + type + "");
    }
    public String getTariffTypeName() {
        return tariffTypeName;
    }
}
