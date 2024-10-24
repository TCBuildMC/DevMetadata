package xyz.tcbuildmc.minecraft.devmetadata.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface BungeeCordPlugin {
    String name();
    String version() default "";
    String author() default "";
    String[] depends() default {};
    String[] softDepends() default {};
    String description() default "";
    String[] libraries() default {};
}
