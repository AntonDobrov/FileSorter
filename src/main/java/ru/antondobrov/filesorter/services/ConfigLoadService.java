package ru.antondobrov.filesorter.services;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.antondobrov.filesorter.controllers.IConfig;
import ru.antondobrov.filesorter.controllers.IConfigLoadService;

public class ConfigLoadService implements IConfigLoadService {
    private IConfig config;
    private final ObjectMapper objectMapper;

    /**
     * Constructs a service that operates on a given configuration object.
     *
     * @param config The application's configuration instance to be managed.
     */
    public ConfigLoadService(IConfig config) {
        this.config = config;

        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Saves the current state of the configuration object to a specified file. Jackson inspects the
     * runtime type of the 'config' object and uses its annotations.
     *
     * @param configFile The file where the configuration will be saved.
     * @return {@code true} if saving was successful, {@code false} otherwise.
     */
    @Override
    public boolean saveConfig(File configFile) {
        try {
            objectMapper.writeValue(configFile, config);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Opens a configuration file and updates the existing configuration object with its data. This
     * method modifies the object that was passed into the constructor, ensuring that any UI
     * components bound to it are updated.
     *
     * @param configFile The file to load the configuration from.
     * @return {@code true} if loading and updating was successful, {@code false} otherwise.
     */
    @Override
    public boolean openConfig(File configFile) {
        if (!configFile.exists() || !configFile.canRead()) {
            return false;
        }

        try {
            objectMapper.readerForUpdating(config).readValue(configFile);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
