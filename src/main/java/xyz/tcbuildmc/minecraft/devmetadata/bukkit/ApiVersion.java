package xyz.tcbuildmc.minecraft.devmetadata.bukkit;

import lombok.Getter;

@Getter
public enum ApiVersion {
    LEGACY,
    VERSION_1_13,
    VERSION_1_14,
    VERSION_1_15,
    VERSION_1_16,
    VERSION_1_17,
    VERSION_1_18,
    VERSION_1_19,
    VERSION_1_20,
    VERSION_1_21;

    ApiVersion() {
    }

    @Override
    public String toString() {
        switch (this) {
            case LEGACY:
                return "";
            case VERSION_1_13:
                return "1.13";
            case VERSION_1_14:
                return "1.14";
            case VERSION_1_15:
                return "1.15";
            case VERSION_1_16:
                return "1.16";
            case VERSION_1_17:
                return "1.17";
            case VERSION_1_18:
                return "1.18";
            case VERSION_1_19:
                return "1.19";
            case VERSION_1_20:
                return "1.20";
            case VERSION_1_21:
                return "1.21";
            default:
                throw new IllegalArgumentException();
        }
    }
}
