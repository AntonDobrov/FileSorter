package ru.antondobrov.filesorter.controllers;

import javafx.beans.property.DoubleProperty;

/**
 * Defines a contract for a configuration model that exposes the progress of the sorting operation.
 * <p>
 * This interface decouples UI components that display progress (like a {@code ProgressBar}) from
 * the concrete service or model that manages the sorting process. By depending on this minimal
 * contract, a controller can bind to the progress property without needing a dependency on the
 * entire sorting system, which improves component isolation and testability.
 */
public interface ISorterConfig {

    /**
     * Returns the {@link DoubleProperty} that represents the current progress of the sorting
     * operation.
     * <p>
     * The value of this property is expected to be updated during the sorting process, typically
     * ranging from 0.0 (0% complete) to 1.0 (100% complete). UI controls, such as a
     * {@code ProgressBar}, can bind their {@code progressProperty} to this one to automatically
     * reflect the status of the operation in real-time.
     *
     * @return The property representing the sorting progress.
     */
    DoubleProperty getSortingProgressProperty();
}
