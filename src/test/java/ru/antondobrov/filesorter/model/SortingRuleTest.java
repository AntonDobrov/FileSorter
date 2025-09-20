package ru.antondobrov.filesorter.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SortingRuleTest {
    private SortingRule rule;

    @BeforeEach
    void setUp() {
        rule = new SortingRule();
    }

    @Test
    void shouldSetAndGetDestinationPath() {
        String destinationPath = anyString();

        rule.setDestinationPath(destinationPath);

        assertThat(rule.getDestinationPath()).isEqualTo(destinationPath);
    }

    @Test
    void shouldReturnNonNullDestinationPathProperty() {
        assertThat(rule.getDestinationPathProperty()).isNotNull();
        assertThat(rule.getDestinationPathProperty().get()).isEqualTo(rule.getDestinationPath());
    }

    @Test
    void shouldAllowAddingAndRetrievingPatterns() {
        ObservableList<String> patterns = rule.getPatterns();
        String pattern = anyString();
        patterns.add(pattern);

        assertThat(rule.getPatterns().size()).isEqualTo(1);
        assertThat(rule.getPatterns().get(0)).isEqualTo(pattern);
    }
}
