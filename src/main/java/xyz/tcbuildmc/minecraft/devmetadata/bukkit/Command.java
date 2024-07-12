package xyz.tcbuildmc.minecraft.devmetadata.bukkit;

import xyz.tcbuildmc.common.annotation.PropertyName;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Command {
    String name();
    String description() default "";
    String usage() default "";
    String[] aliases() default {};
    String permission() default "";

    @PropertyName("permission-message")
    String permissionMessage() default "";
}
