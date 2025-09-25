package ru.antondobrov.filesorter.controllers;

import java.io.File;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import ru.antondobrov.filesorter.services.IDirectoryChooserService;
import ru.antondobrov.filesorter.services.IRuleConfig;
import ru.antondobrov.filesorter.services.IRuleService;

public class RuleController {
    IRuleConfig config;
    IRuleService ruleService;
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
    void onChoiceDestinationPathButtonClick(ActionEvent event) {
        File destinationDirectory = directoryChooserService.showDialog(event);

        if (destinationDirectory == null) {
            return;
        }

        config.getDestinationDirectoryPathProperty().set(destinationDirectory.getAbsolutePath());
    }

    @FXML
    void onDeletePatternButtonClick(ActionEvent event) {
        ObservableList<Integer> indices = patternsListView.getSelectionModel().getSelectedIndices();
        if (indices.isEmpty()) {
            return;
        }
        ruleService.deletePatternsFromPatternsList(config.getPatternList(), indices);
    }

    @FXML
    void onAddPatternButtonClick(ActionEvent event) {
        Integer defaultNewPatterns = 1;
        ruleService.addPatternsToPatternsList(config.getPatternList(), defaultNewPatterns);
    }

    @FXML
    void initialize() {
        patternsListView.setEditable(true);
        patternsListView.setCellFactory(TextFieldListCell.forListView());
        destinationPathTextField.textProperty()
                .bindBidirectional(config.getDestinationDirectoryPathProperty());
        patternsListView.setItems(config.getPatternList());
    }
}
