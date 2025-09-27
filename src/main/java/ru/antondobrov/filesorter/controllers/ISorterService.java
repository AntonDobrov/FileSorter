package ru.antondobrov.filesorter.controllers;

/**
 * Defines the contract for a service responsible for initiating the file sorting process.
 * <p>
 * This interface abstracts the core sorting logic from the UI controllers. By depending on this
 * contract, a controller can trigger the sorting operation without needing to know the details of
 * its implementation (e.g., whether it runs synchronously, asynchronously in a background thread,
 * or uses a specific sorting algorithm).
 */
public interface ISorterService {

    /**
     * Starts the file sorting operation.
     * <p>
     * The implementation of this method should contain all the logic required to read the current
     * configuration, scan the specified directories, and move or copy files according to the
     * defined rules.
     */
    void startSorting();
}
