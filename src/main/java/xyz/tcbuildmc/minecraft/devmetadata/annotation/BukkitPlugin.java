package xyz.tcbuildmc.minecraft.devmetadata.annotation;

import com.google.common.annotations.Beta;
import xyz.tcbuildmc.common.annotation.PropertyName;
import xyz.tcbuildmc.minecraft.devmetadata.bukkit.ApiVersion;
import xyz.tcbuildmc.minecraft.devmetadata.bukkit.LoadOrder;
import xyz.tcbuildmc.minecraft.devmetadata.bukkit.PermissionBase;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface BukkitPlugin {
    String name();
    String version();

    String description() default "";
    String author() default "";
    String[] authors() default {};
    String[] contributors() default {};
    String website() default "";

    @PropertyName("folia-supported")
    boolean supportsFolia() default false;

    @PropertyName("api-version")
    ApiVersion apiVersion() default ApiVersion.LEGACY;

    LoadOrder load() default LoadOrder.POSTWORLD;
    String prefix() default "";

    @PropertyName("default-permission")
    PermissionBase defaultPermission() default PermissionBase.OP;

    @Beta
    String[] libraries() default {};

    String[] depend() default {};
    String[] softdepend() default {};
    String[] loadbefore() default {};

    @Beta
    String[] provides() default {};
}
