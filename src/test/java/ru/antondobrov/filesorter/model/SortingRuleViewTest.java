package ru.antondobrov.filesorter.model;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortingRuleViewTest {
    private SortingRule rule;
    private SortingRuleView ruleView;

    @BeforeEach
    void setUp() {
        rule = new SortingRule("/home/user/test1");
        ruleView = new SortingRuleView(rule);
    }

    @Test
    void shouldSetAndGetRule() {
        SortingRule newRule = new SortingRule("/home/user/test2");
        ruleView.setRule(newRule);
        assertThat(newRule).isEqualTo(ruleView.getRule()).isNotEqualTo(rule);
    }

    @Test
    void shouldReturnNonNullRuleProperty() {
        assertThat(ruleView.getRuleProperty()).isNotNull();
        assertThat(ruleView.getRuleProperty().get()).isEqualTo(ruleView.getRule());
    }
}
