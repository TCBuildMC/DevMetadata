package xyz.tcbuildmc.minecraft.devmetadata.bukkit;

public enum PermissionBase {
    FALSE,
    TRUE,
    OP,
    NOT_OP;

    PermissionBase() {
    }

    @Override
    public String toString() {
        switch (this) {
            case FALSE:
                return "false";
            case TRUE:
                return "true";
            case OP:
                return "op";
            case NOT_OP:
                return "not op";
            default:
                throw new IllegalArgumentException();
        }
    }
}
