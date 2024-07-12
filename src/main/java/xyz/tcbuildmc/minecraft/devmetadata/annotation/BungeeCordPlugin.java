package xyz.tcbuildmc.minecraft.devmetadata.annotation;

public @interface BungeeCordPlugin {
    String name();
    String version() default "";
    String author() default "";
    String[] depends() default {};
    String[] softDepends() default {};
    String description() default "";
    String[] libraries() default {};
}
