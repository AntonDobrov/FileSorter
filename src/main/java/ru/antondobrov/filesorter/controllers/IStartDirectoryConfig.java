package ru.antondobrov.filesorter.controllers;

import javafx.beans.property.StringProperty;

/**
 * Defines a contract for a configuration model that provides access to the start directory path
 * property.
 * <p>
 * This interface is used to decouple UI controllers from the concrete implementation of the main
 * configuration object. By depending on this contract, a controller can interact with any object
 * that can provide the start directory path as a bindable {@link StringProperty}, enhancing
 * modularity and testability.
 */
public interface IStartDirectoryConfig {

    /**
     * Returns the {@link StringProperty} for the start directory path.
     * <p>
     * The returned property allows UI components (e.g., a {@code TextField}) to be bound directly
     * to the configuration's start directory path. This enables automatic synchronization between
     * the model and the view.
     *
     * @return The property representing the start directory path.
     */
    StringProperty getStartDirectoryPathProperty();
}
