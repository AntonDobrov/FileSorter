package ru.antondobrov.filesorter.controllers;

import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;
import ru.antondobrov.filesorter.model.ActionPolicy;

public class AdditionalSettingsPanelController {
    private final IAdditionalSettingsConfig config;
    private final StringConverter<ActionPolicy> converter;

    public AdditionalSettingsPanelController(IAdditionalSettingsConfig config,
            StringConverter<ActionPolicy> converter) {
        this.config = config;
        this.converter = converter;
    }

    @FXML
    private ChoiceBox<ActionPolicy> traverseSubdirectoriesChoiceBox;
    @FXML
    private ChoiceBox<ActionPolicy> deleteOnSuccessChoiceBox;
    @FXML
    private ChoiceBox<ActionPolicy> copyInDestinationExistChoiceBox;

    private void configureBox(ChoiceBox<ActionPolicy> box, List<ActionPolicy> boxItems,
            ObjectProperty<ActionPolicy> property) {
        box.setConverter(converter);
        box.getItems().setAll(boxItems);
        box.valueProperty().bindBidirectional(property);
    }

    @FXML
    void initialize() {
        configureBox(traverseSubdirectoriesChoiceBox, List.of(ActionPolicy.YES, ActionPolicy.NO),
                config.getTraverseSubdirectoriesPolicyProperty());

        configureBox(deleteOnSuccessChoiceBox,
                List.of(ActionPolicy.YES, ActionPolicy.NO, ActionPolicy.ASK),
                config.getDeleteOnSuccessPolicyProperty());

        configureBox(copyInDestinationExistChoiceBox,
                List.of(ActionPolicy.YES, ActionPolicy.NO, ActionPolicy.ASK),
                config.getDuplicateFilesPolicyProperty());
    }
}
