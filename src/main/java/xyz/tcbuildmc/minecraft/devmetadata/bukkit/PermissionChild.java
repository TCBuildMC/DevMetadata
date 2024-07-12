package xyz.tcbuildmc.minecraft.devmetadata.bukkit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface PermissionChild {
    String name();
    boolean extend() default true;
}
