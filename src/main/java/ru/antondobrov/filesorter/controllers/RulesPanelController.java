package ru.antondobrov.filesorter.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import ru.antondobrov.filesorter.model.IRuleConfig;
import ru.antondobrov.filesorter.utils.RuleCell;

public class RulesPanelController {
    @FXML
    ListView<IRuleConfig> rulesListView;

    private final IRulesConfig config;
    private final IRulesService rulesService;
    private final IRuleService ruleService;
    private final IDirectoryChooserService chooserService;

    public RulesPanelController(IRulesConfig config, IRulesService rulesService,
            IRuleService ruleService, IDirectoryChooserService chooserService) {
        this.config = config;
        this.rulesService = rulesService;
        this.ruleService = ruleService;
        this.chooserService = chooserService;
    }

    @FXML
    public void onAddNewRuleButtonClick(ActionEvent event) {
        rulesService.addNewRuleInRulesList(config.getRulesList());
    }

    @FXML
    public void onDeleteRuleButtonClick(ActionEvent event) {
        ObservableList<Integer> indices = rulesListView.getSelectionModel().getSelectedIndices();
        if (indices.isEmpty()) {
            return;
        }
        rulesService.deleteRulesInRulesList(config.getRulesList(), indices);
    }

    @FXML
    void initialize() {
        rulesListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        rulesListView.setCellFactory(listView -> new RuleCell(ruleService, chooserService));
        rulesListView.setItems(config.getRulesList());
    }
}
