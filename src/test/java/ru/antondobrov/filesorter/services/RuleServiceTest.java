package ru.antondobrov.filesorter.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RuleServiceTest {

    String pattern1 = "1";
    String pattern2 = "2";
    ObservableList<String> patternsList;
    RuleService service;

    @BeforeEach
    void setUp() {
        patternsList = FXCollections.observableArrayList();
        patternsList.setAll(pattern1, pattern2);
        service = new RuleService();
    }

    @Test
    void shouldAddEmptyPatternsInPatternListIfListIsNotNull() {
        service.addPatternsToPatternsList(patternsList, 1);

        assertThat(patternsList).hasSize(3).contains(pattern1, pattern2, "");
    }

    @Test
    void shouldNotThrowExceptionWhenAddingToNullList() {
        assertDoesNotThrow(() -> service.addPatternsToPatternsList(null, 1));
    }

    @Test
    void shouldDeletePatternsInPatternListIfListIsNotNull() {
        ObservableList<Integer> indices = FXCollections.observableArrayList(0, 1);

        service.deletePatternsFromPatternsList(patternsList, indices);

        assertThat(patternsList).isEmpty();
    }

    @Test
    void shouldNotThrowExceptionWhenDeletingFromNullList() {
        ObservableList<Integer> indices = FXCollections.observableArrayList(0);

        assertDoesNotThrow(() -> service.deletePatternsFromPatternsList(null, indices));
    }

    @Test
    void shouldNotThrowExceptionWhenDeletingWithNullIndices() {
        assertDoesNotThrow(() -> service.deletePatternsFromPatternsList(patternsList, null));
    }
}
