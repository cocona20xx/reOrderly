package io.github.cocona20xx.reorderly.config_old;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import io.github.cocona20xx.reorderly.ReOrderlyMod;
import org.quiltmc.loader.api.QuiltLoader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class OrderlyConfigManager {

    private static final Executor EXECUTOR = Executors.newSingleThreadExecutor(r -> new Thread(r, "ReOrderlyMod Config Manager"));
    private static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
    private static ReOrderlyConfigAccessorImpl config;
    private static Path configFile;

    public static ReOrderlyConfigAccessorImpl getConfig() {
        return config != null ? config : init();
    }

    public static ReOrderlyConfigAccessorImpl init() {
        configFile = QuiltLoader.getConfigDir().resolve(ReOrderlyMod.MODID + ".json");
        if(!Files.exists(configFile)) {
            ReOrderlyMod.getLogger().info("creating orderly config file ({})", configFile::getFileName);
            save().join();
        }
        load().thenApply(c -> config = c).join();
        return Objects.requireNonNull(config, "failed to init config");
    }

    public static CompletableFuture<ReOrderlyConfigAccessorImpl> load() {
        return CompletableFuture.supplyAsync(() -> {
            try(BufferedReader reader = Files.newBufferedReader(configFile)) {
                return GSON.fromJson(reader, ReOrderlyConfigAccessorImpl.class);
            }
            catch (IOException | JsonParseException e) {
                ReOrderlyMod.getLogger().error("unable to read config file, restoring defaults!", e);
                save();
                return new ReOrderlyConfigAccessorImpl();
            }
        }, EXECUTOR);
    }

    public static CompletableFuture<Void> save() {
        ReOrderlyMod.getLogger().trace("saving orderly config file to {}", configFile);
        return CompletableFuture.runAsync(() -> {
            try(BufferedWriter writer = Files.newBufferedWriter(configFile)) {
                GSON.toJson(Optional.ofNullable(config).orElseGet(ReOrderlyConfigAccessorImpl::new), writer);
            }
            catch (IOException | JsonIOException e) {
                ReOrderlyMod.getLogger().error("unable to write config file", e);
            }
        }, EXECUTOR);
    }
}
