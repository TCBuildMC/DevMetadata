package xyz.tcbuildmc.minecraft.devmetadata.processor;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.google.auto.service.AutoService;
import xyz.tcbuildmc.minecraft.devmetadata.annotation.BukkitPlugin;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@AutoService(Processor.class)
@SupportedAnnotationTypes({"xyz.tcbuildmc.minecraft.devmetadata.annotation.BukkitPlugin"})
public class BukkitProcessor extends AbstractProcessor {
    private Messager messager;
    private Filer filer;
    private String foundedClassName = null;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        this.messager = processingEnv.getMessager();
        this.filer = processingEnv.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            return false;
        }

        for (Element element : roundEnv.getElementsAnnotatedWith(BukkitPlugin.class)) {
            if (element.getKind() != ElementKind.CLASS) {
                this.messager.printMessage(Diagnostic.Kind.ERROR, "Only classes can be annotated with @" + BukkitPlugin.class.getCanonicalName());
                return false;
            }

            String name = ((TypeElement) element).getQualifiedName().toString();
            if (this.foundedClassName == null) {
                this.foundedClassName = name;
            } else {
                this.messager.printMessage(Diagnostic.Kind.WARNING, "Found multiple classes were annotated with @" + BukkitPlugin.class.getCanonicalName());
                this.messager.printMessage(Diagnostic.Kind.WARNING, "Bukkit plugin metadata support only one main entrypoint.");
                return false;
            }

            BukkitPlugin plugin = element.getAnnotation(BukkitPlugin.class);

            if (plugin.name().isEmpty() || plugin.version().isEmpty()) {
                this.messager.printMessage(Diagnostic.Kind.ERROR, "Bukkit plugin metadata required specified name/version!");
                return false;
            }

            Map<String, Object> metadata = new LinkedHashMap<>();
            metadata.put("name", plugin.name());
            metadata.put("version", plugin.version());
            metadata.put("main", this.foundedClassName);
            metadata.put("description", plugin.description());
            metadata.put("author", plugin.author());
            metadata.put("authors", Arrays.asList(plugin.authors()));
            metadata.put("contributors", Arrays.asList(plugin.contributors()));
            metadata.put("website", plugin.website());
            metadata.put("folia-supported", plugin.supportsFolia());
            metadata.put("api-version", plugin.apiVersion().toString());
            metadata.put("load", plugin.load().name());
            metadata.put("default-permission", plugin.defaultPermission().toString());
            metadata.put("libraries", Arrays.asList(plugin.libraries()));
            metadata.put("depend", Arrays.asList(plugin.depend()));
            metadata.put("softdepend", Arrays.asList(plugin.softdepend()));
            metadata.put("loadbefore", Arrays.asList(plugin.loadbefore()));
            metadata.put("provides", Arrays.asList(plugin.provides()));

            try {
                FileObject fileObject = filer.createResource(StandardLocation.CLASS_OUTPUT, "", "plugin.yml");
                YAMLMapper mapper = YAMLMapper.builder()
                        .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
                        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                        .build();

                try (OutputStream os = fileObject.openOutputStream()) {
                    mapper.writer().withDefaultPrettyPrinter().writeValue(os, metadata);
                }
            } catch (IOException e) {
                this.messager.printMessage(Diagnostic.Kind.ERROR, "Failed to create the plugin metadata file.");
            }
        }

        return false;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
