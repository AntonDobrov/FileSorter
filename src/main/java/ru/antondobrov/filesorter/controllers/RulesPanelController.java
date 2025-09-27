package ru.antondobrov.filesorter.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import ru.antondobrov.filesorter.model.IRuleConfig;
import ru.antondobrov.filesorter.utils.RuleCell;

/**
 * Controller for the UI panel that displays and manages the list of sorting rules.
 * <p>
 * This class orchestrates the interaction with the list of sorting rules. It uses a
 * {@link ListView} to display the rules from a configuration model ({@link IRulesConfig}). User
 * actions, such as adding or deleting rules, are delegated to specialized services. A key
 * responsibility of this controller is to configure the {@code ListView} with a custom
 * {@link RuleCell} factory, which defines the appearance and behavior of each individual rule row
 * in the list.
 */
public class RulesPanelController {
    /** The ListView that displays the collection of sorting rules. */
    @FXML
    private ListView<IRuleConfig> rulesListView;

    private final IRulesConfig config;
    private final IRulesService rulesService;
    private final IRuleService ruleService;
    private final IDirectoryChooserService chooserService;

    /**
     * Constructs a new controller with its required dependencies.
     *
     * @param config The configuration model that provides the {@link ObservableList} of rules.
     * @param rulesService The service for collection-level operations, like adding a new rule or
     *        deleting a set of rules.
     * @param ruleService The service for operations on a single rule. It is passed down to the
     *        {@link RuleCell} to handle logic within each cell.
     * @param chooserService The directory chooser service, also passed down to the
     *        {@link RuleCell}, enabling directory selection for individual rules.
     */
    public RulesPanelController(IRulesConfig config, IRulesService rulesService,
            IRuleService ruleService, IDirectoryChooserService chooserService) {
        this.config = config;
        this.rulesService = rulesService;
        this.ruleService = ruleService;
        this.chooserService = chooserService;
    }

    /**
     * Event handler for the 'Add New Rule' button click.
     * <p>
     * It delegates the action to the {@link IRulesService}, which creates a new default rule and
     * adds it to the list of rules in the configuration model.
     *
     * @param event The {@link ActionEvent} triggered by the button click.
     */
    @FXML
    public void onAddNewRuleButtonClick(ActionEvent event) {
        rulesService.addNewRuleInRulesList(config.getRulesList());
    }

    /**
     * Event handler for the 'Delete Rule' button click.
     * <p>
     * This method retrieves the indices of all selected rules from the {@code ListView}. If at
     * least one rule is selected, it calls the {@link IRulesService} to remove the corresponding
     * rules from the configuration model's list.
     *
     * @param event The {@link ActionEvent} triggered by the button click.
     */
    @FXML
    public void onDeleteRuleButtonClick(ActionEvent event) {
        ObservableList<Integer> indices = rulesListView.getSelectionModel().getSelectedIndices();
        if (indices.isEmpty()) {
            return;
        }
        rulesService.deleteRulesInRulesList(config.getRulesList(), indices);
    }

    /**
     * Initializes the controller and configures the {@code rulesListView}.
     * <p>
     * Sets a custom {@code CellFactory} (using {@link RuleCell}) to control the rendering and
     * behavior of each row. The necessary services are passed to the cell factory so that each cell
     * can be fully interactive.
     */
    @FXML
    void initialize() {
        rulesListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        rulesListView.setCellFactory(listView -> new RuleCell(ruleService, chooserService));
        rulesListView.setItems(config.getRulesList());
    }
}
