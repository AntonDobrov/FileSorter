package ru.antondobrov.filesorter.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.lang.reflect.Field;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.framework.junit5.ApplicationExtension;
import ru.antondobrov.filesorter.model.IRuleConfig;

@ExtendWith(ApplicationExtension.class)
@ExtendWith(MockitoExtension.class)
class RulesPanelControllerTest {
    @Mock
    private IRulesConfig config;
    @Mock
    private IRulesService rulesService;
    @Mock
    private IRuleService ruleService;
    @Mock
    private IDirectoryChooserService chooserService;


    private ObservableList<IRuleConfig> rulesList;
    @Mock
    private IRuleConfig rule1;
    @Mock
    private IRuleConfig rule2;

    private ListView<IRuleConfig> rulesListView;
    private RulesPanelController controller;

    @BeforeEach
    void setUp() throws Exception {
        rulesList = FXCollections.observableArrayList(rule1, rule2);
        when(config.getRulesList()).thenReturn(rulesList);

        controller = new RulesPanelController(config, rulesService, ruleService, chooserService);

        rulesListView = new ListView<>();
        Field field = RulesPanelController.class.getDeclaredField("rulesListView");
        field.setAccessible(true);
        field.set(controller, rulesListView);

        controller.initialize();
    }

    @Test
    void shouldCallServiceToDeleteRuleWhenRuleIsSelected() {
        rulesListView.getSelectionModel().select(1);
        ObservableList<Integer> expectedIndices =
                rulesListView.getSelectionModel().getSelectedIndices();

        controller.onDeleteRuleButtonClick(null);

        verify(rulesService).deleteRulesInRulesList(rulesList, expectedIndices);
    }

    @Test
    void shouldNotCallServiceToDeleteRuleWhenNothingIsSelected() {
        controller.onDeleteRuleButtonClick(null);

        verify(rulesService, never()).deleteRulesInRulesList(any(), any());
    }

    @Test
    void shouldCallServiceToAddRuleWhenAddButtonClicked() {
        controller.onAddNewRuleButtonClick(null);

        verify(rulesService).addNewRuleInRulesList(rulesList);
    }
}
