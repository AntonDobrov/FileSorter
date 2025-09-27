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

/**
 * Controller for the user interface of a single sorting rule.
 * <p>
 * This class manages the UI components for an individual rule. It is designed for reusability
 * within the context of a {@code ListView}'s cell recycling mechanism. A single instance is created
 * by its parent cell (e.g., {@code RuleCell}), and its state is updated dynamically via the
 * {@link #setData(IRuleConfig)} method whenever the cell is repurposed to display a different data
 * item.
 */
public class RuleController {
    private IRuleConfig config;
    private final IRuleService ruleService;
    private final IDirectoryChooserService directoryChooserService;

    /** A text field to display and edit the destination directory path for this rule. */
    @FXML
    private TextField destinationPathTextField;

    /**
     * A list view to display and manage the file patterns (e.g., "*.jpg", "*.txt") associated with
     * this rule.
     */
    @FXML
    private ListView<String> patternsListView;

    /**
     * Constructs a new, reusable controller for a single rule's UI.
     * <p>
     * The controller is initialized without a specific data model; the data must be provided later
     * by calling the {@link #setData(IRuleConfig)} method.
     *
     * @param ruleService The service for modifying the rule's list of patterns.
     * @param directoryChooserService The service for displaying a directory selection dialog.
     */
    public RuleController(IRuleService ruleService,
            IDirectoryChooserService directoryChooserService) {
        this.ruleService = ruleService;
        this.directoryChooserService = directoryChooserService;
    }

    /**
     * Event handler for the 'Choose Destination Path' button.
     * <p>
     * Invokes the {@code directoryChooserService} to open a dialog. If the user selects a
     * directory, its absolute path is set on the rule's currently configured model.
     *
     * @param event The {@link ActionEvent} used to determine the owner window for the dialog.
     */
    @FXML
    public void onChoiceDestinationPathButtonClick(ActionEvent event) {
        File destinationDirectory = directoryChooserService.showDialog(event);

        if (destinationDirectory == null) {
            return;
        }

        config.getDestinationPathProperty().set(destinationDirectory.getAbsolutePath());
    }

    /**
     * Event handler for the 'Delete Pattern' button.
     * <p>
     * Retrieves the selected items from the patterns list. If any are selected, it delegates the
     * deletion task to the {@link IRuleService}.
     *
     * @param event The {@link ActionEvent} triggered by the button click.
     */
    @FXML
    public void onDeletePatternButtonClick(ActionEvent event) {
        ObservableList<Integer> indices = patternsListView.getSelectionModel().getSelectedIndices();
        if (indices.isEmpty()) {
            return;
        }
        ruleService.deletePatternsFromPatternsList(config.getPatterns(), indices);
    }

    /**
     * Event handler for the 'Add Pattern' button.
     * <p>
     * Delegates to the {@link IRuleService} to add a new, editable entry to the list of patterns.
     *
     * @param event The {@link ActionEvent} triggered by the button click.
     */
    @FXML
    public void onAddPatternButtonClick(ActionEvent event) {
        ruleService.addPatternsToPatternsList(config.getPatterns());
    }

    /**
     * Updates the controller to display the data from a new rule configuration.
     * <p>
     *
     * @param newConfig The new data model to be displayed by this controller.
     */
    public void setData(IRuleConfig newConfig) {
        if (this.config != null) {
            destinationPathTextField.textProperty()
                    .unbindBidirectional(this.config.getDestinationPathProperty());
        }

        this.config = newConfig;

        destinationPathTextField.textProperty()
                .bindBidirectional(this.config.getDestinationPathProperty());
        patternsListView.setItems(this.config.getPatterns());
    }

    @FXML
    void initialize() {
        patternsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        patternsListView.setCellFactory(TextFieldListCell.forListView());
    }
}
