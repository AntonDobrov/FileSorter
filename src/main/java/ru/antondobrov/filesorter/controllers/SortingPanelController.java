package ru.antondobrov.filesorter.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

public class SortingPanelController {
    private final ISorterService sorterService;
    private final ISorterConfig config;

    public SortingPanelController(ISorterService sorterService, ISorterConfig config) {
        this.sorterService = sorterService;
        this.config = config;
    }

    @FXML
    ProgressBar sortingProgressBar;

    @FXML
    public void onStartSortingButtonClick(ActionEvent event) {
        sorterService.startSorting();
    }

    @FXML
    void initialize() {
        sortingProgressBar.progressProperty().bind(config.getSortingProgressProperty());
    }
}
