package ru.antondobrov.filesorter.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import ru.antondobrov.filesorter.services.ISorterConfig;
import ru.antondobrov.filesorter.services.ISorterService;

public class SortingPanelController {
    ISorterService sorterService;
    ISorterConfig config;

    public SortingPanelController(ISorterService sorterService, ISorterConfig config) {
        this.sorterService = sorterService;
        this.config = config;
    }

    @FXML
    ProgressBar sortingProgressBar;

    @FXML
    void onStartSortingButtonClick(ActionEvent event) {
        sorterService.startSorting();
    }

    @FXML
    void initialize() {
        sortingProgressBar.progressProperty().bind(config.getSortingProgressProperty());
    }
}
