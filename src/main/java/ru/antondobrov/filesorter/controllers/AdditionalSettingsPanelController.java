package ru.antondobrov.filesorter.controllers;

import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;
import ru.antondobrov.filesorter.model.ActionPolicy;

/**
 * Controller for the 'Additional Settings' panel of the user interface.
 * <p>
 * This class is responsible for initializing and managing the UI controls related to advanced
 * sorting options, such as traversing subdirectories, deleting original files, and handling
 * duplicates. It uses a configuration model (implementing {@link IAdditionalSettingsConfig}) to
 * store the state of these settings and employs bidirectional data binding to keep the UI and the
 * model synchronized.
 */
public class AdditionalSettingsPanelController {
    private final IAdditionalSettingsConfig config;
    private final StringConverter<ActionPolicy> converter;

    /**
     * Constructs a new controller instance with its required dependencies.
     *
     * @param config The configuration model that holds the state of the settings and provides
     *        properties for data binding.
     * @param converter The converter used to display {@link ActionPolicy} enums as localized,
     *        human-readable text in the {@code ChoiceBox} controls.
     */
    public AdditionalSettingsPanelController(IAdditionalSettingsConfig config,
            StringConverter<ActionPolicy> converter) {
        this.config = config;
        this.converter = converter;
    }

    /** A {@code ChoiceBox} to select the policy for traversing subdirectories. */
    @FXML
    private ChoiceBox<ActionPolicy> traverseSubdirectoriesChoiceBox;

    /**
     * A {@code ChoiceBox} to select the policy for deleting source files after a successful move.
     */
    @FXML
    private ChoiceBox<ActionPolicy> deleteOnSuccessChoiceBox;

    /**
     * A {@code ChoiceBox} to select the policy for handling files that already exist in the
     * destination directory (duplicates).
     */
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
