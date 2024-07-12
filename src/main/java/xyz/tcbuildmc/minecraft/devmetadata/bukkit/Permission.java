package xyz.tcbuildmc.minecraft.devmetadata.bukkit;

import xyz.tcbuildmc.common.annotation.PropertyName;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Permission {
    String name();
    String description() default "";

    @PropertyName("default")
    PermissionBase defaultPermission() default PermissionBase.OP;

    PermissionChild[] children() default {};
}
