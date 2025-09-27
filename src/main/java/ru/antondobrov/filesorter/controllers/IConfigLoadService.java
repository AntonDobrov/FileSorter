package ru.antondobrov.filesorter.controllers;

import java.io.File;

/**
 * Defines a contract for a service responsible for saving (persisting) and loading the
 * application's configuration.
 * <p>
 * This interface abstracts the underlying mechanism of configuration persistence (e.g.,
 * serialization to JSON, XML, or another format) from the UI controllers. By depending on this
 * contract, a controller like {@link ConfigLoadPanelController} can initiate save and load
 * operations without being coupled to the specific implementation details, which promotes
 * modularity and makes the system easier to test and maintain.
 */
public interface IConfigLoadService {

    /**
     * Saves the current state of the application's configuration to the specified file.
     * <p>
     * The implementation should gather the current configuration data from the application's model
     * and serialize it into the given file.
     *
     * @param configFile The target file where the configuration will be written. Must not be
     *        {@code null}.
     * @return {@code true} if the configuration was saved successfully; {@code false} otherwise
     *         (e.g., due to an I/O error or invalid permissions).
     */
    boolean saveConfig(File configFile);

    /**
     * Loads the application's configuration from the specified file.
     * <p>
     * The implementation should read and deserialize the data from the given file, and then update
     * the application's configuration model with the loaded settings. This action should typically
     * trigger UI updates to reflect the new state.
     *
     * @param configFile The source file from which the configuration will be read. Must not be
     *        {@code null}.
     * @return {@code true} if the configuration was loaded and applied successfully; {@code false}
     *         otherwise (e.g., if the file does not exist, is corrupt, or an I/O error occurs).
     */
    boolean openConfig(File configFile);
}
