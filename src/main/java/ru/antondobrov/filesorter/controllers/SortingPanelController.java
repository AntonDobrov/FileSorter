package ru.antondobrov.filesorter.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

/**
 * Controller for the UI panel that initiates the sorting process and displays its progress.
 * <p>
 * This class connects a 'Start Sorting' button to the core sorting logic, encapsulated within an
 * {@link ISorterService}. It also visualizes the sorting progress by binding a {@link ProgressBar}
 * to a progress property provided by the {@link ISorterConfig} model. This decouples the UI from
 * the actual sorting implementation, allowing the UI to reactively update as the process unfolds.
 */
public class SortingPanelController {
    private final ISorterService sorterService;
    private final ISorterConfig config;

    /**
     * Constructs a new controller with its required service and configuration dependencies.
     *
     * @param sorterService The service that encapsulates the core file sorting logic. This
     *        controller will invoke its {@code startSorting} method.
     * @param config The configuration model which provides the observable property for the sorting
     *        progress, allowing the UI to bind to it.
     */
    public SortingPanelController(ISorterService sorterService, ISorterConfig config) {
        this.sorterService = sorterService;
        this.config = config;
    }

    /**
     * The progress bar that visually represents the current status of the file sorting operation.
     */
    @FXML
    private ProgressBar sortingProgressBar;

    /**
     * Event handler for the 'Start Sorting' button click.
     * <p>
     * Delegates the call to the {@link ISorterService} to initiate the file sorting process.
     *
     * @param event The {@link ActionEvent} triggered by the button click.
     */
    @FXML
    public void onStartSortingButtonClick(ActionEvent event) {
        sorterService.startSorting();
    }

    @FXML
    void initialize() {
        sortingProgressBar.progressProperty().bind(config.getSortingProgressProperty());
    }
}
