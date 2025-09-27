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

@ExtendWith(MockitoExtension.class)
class RuleServiceTest {

    @Mock
    private PatternFactory factory;

    String pattern1 = "1";
    String pattern2 = "2";
    ObservableList<String> patternsList;
    RuleService service;

    @BeforeEach
    void setUp() {
        patternsList = FXCollections.observableArrayList();
        patternsList.setAll(pattern1, pattern2);
        service = new RuleService(factory);
    }

    @Test
    void shouldAddEmptyPatternsInPatternListIfListIsNotNull() {
        when(factory.create()).thenReturn("");
        service.addPatternsToPatternsList(patternsList);

        assertThat(patternsList).hasSize(3).contains(pattern1, pattern2, "");
    }

    @Test
    void shouldNotThrowExceptionWhenAddingToNullList() {
        assertDoesNotThrow(() -> service.addPatternsToPatternsList(null));
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
