package ru.antondobrov.filesorter.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.antondobrov.filesorter.model.IRuleConfig;
import ru.antondobrov.filesorter.utils.RuleFactory;

@ExtendWith(MockitoExtension.class)
class RulesServiceTest {

    @Mock
    private RuleFactory factory;

    private ObservableList<IRuleConfig> rulesList;

    private RulesService service;

    @Mock
    private IRuleConfig rule;

    @BeforeEach
    void setUp() {
        rulesList = FXCollections.observableArrayList();
        service = new RulesService(factory);
    }

    @Test
    void shouldAddNewRuleInRulesListIfListIsNotNull() {
        when(factory.create()).thenReturn(rule);

        service.addNewRuleInRulesList(rulesList);

        assertThat(rulesList).hasSize(1).contains(rule);
    }

    @Test
    void shouldNotThrowExceptionWhenAddingToNullList() {
        assertDoesNotThrow(() -> service.addNewRuleInRulesList(null));
    }

    @Test
    void shouldDeleteRulesInRulesListIfListIsNotNull() {
        ObservableList<Integer> indices = FXCollections.observableArrayList(0, 1);
        service.addNewRuleInRulesList(rulesList);
        service.addNewRuleInRulesList(rulesList);

        service.deleteRulesInRulesList(rulesList, indices);

        assertThat(rulesList).isEmpty();
    }

    @Test
    void shouldNotThrowExceptionWhenDeletingFromNullList() {
        ObservableList<Integer> indices = FXCollections.observableArrayList(0);

        assertDoesNotThrow(() -> service.deleteRulesInRulesList(null, indices));
    }

    @Test
    void shouldNotThrowExceptionWhenDeletingWithNullIndices() {
        assertDoesNotThrow(() -> service.deleteRulesInRulesList(rulesList, null));
    }
}
