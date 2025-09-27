package ru.antondobrov.filesorter.controllers;

import java.io.File;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import ru.antondobrov.filesorter.model.IRuleConfig;

public class RuleController {
    private final IRuleConfig config;
    private final IRuleService ruleService;
    @FXML
    TextField destinationPathTextField;
    @FXML
    ListView<String> patternsListView;

    IDirectoryChooserService directoryChooserService;

    public RuleController(IRuleConfig config, IRuleService ruleService,
            IDirectoryChooserService directoryChooserService) {
        this.config = config;
        this.ruleService = ruleService;
        this.directoryChooserService = directoryChooserService;
    }

    @FXML
    public void onChoiceDestinationPathButtonClick(ActionEvent event) {
        File destinationDirectory = directoryChooserService.showDialog(event);

        if (destinationDirectory == null) {
            return;
        }

        config.getDestinationPathProperty().set(destinationDirectory.getAbsolutePath());
    }

    @FXML
    public void onDeletePatternButtonClick(ActionEvent event) {
        ObservableList<Integer> indices = patternsListView.getSelectionModel().getSelectedIndices();
        if (indices.isEmpty()) {
            return;
        }
        ruleService.deletePatternsFromPatternsList(config.getPatterns(), indices);
    }

    @FXML
    public void onAddPatternButtonClick(ActionEvent event) {
        ruleService.addPatternsToPatternsList(config.getPatterns());
    }

    @FXML
    void initialize() {
        patternsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        patternsListView.setCellFactory(TextFieldListCell.forListView());
        destinationPathTextField.textProperty()
                .bindBidirectional(config.getDestinationPathProperty());
        patternsListView.setItems(config.getPatterns());
    }
}
