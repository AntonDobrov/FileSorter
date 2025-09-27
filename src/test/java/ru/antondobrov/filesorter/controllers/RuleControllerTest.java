package ru.antondobrov.filesorter.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.File;
import java.lang.reflect.Field;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.framework.junit5.ApplicationExtension;
import ru.antondobrov.filesorter.model.IRuleConfig;

@ExtendWith(ApplicationExtension.class)
@ExtendWith(MockitoExtension.class)
class RuleControllerTest {
    @Mock
    IRuleConfig config;
    @Mock
    IRuleService ruleService;
    @Mock
    IDirectoryChooserService directoryChooserService;

    ListView<String> patternsListView;
    TextField destinationDirectoryField;

    StringProperty pathProperty;

    String testPath = "/home/user/test";
    ObservableList<String> patternsList;
    String pattern1 = "pattern1";
    String pattern2 = "pattern2";

    RuleController controller;

    @BeforeEach
    void setUp() throws Exception {
        pathProperty = new SimpleStringProperty();
        pathProperty.set(testPath);
        patternsList = FXCollections.observableArrayList();
        patternsList.addAll(pattern1, pattern2);
        when(config.getDestinationPathProperty()).thenReturn(pathProperty);
        when(config.getPatterns()).thenReturn(patternsList);

        controller = new RuleController(ruleService, directoryChooserService);


        patternsListView = new ListView<String>();
        Field patternsListViewFromController =
                RuleController.class.getDeclaredField("patternsListView");
        patternsListViewFromController.setAccessible(true);
        patternsListViewFromController.set(controller, patternsListView);

        destinationDirectoryField = new TextField();
        Field destinationDirectoryFieldFromController =
                RuleController.class.getDeclaredField("destinationPathTextField");
        destinationDirectoryFieldFromController.setAccessible(true);
        destinationDirectoryFieldFromController.set(controller, destinationDirectoryField);

        controller.setData(config);
        controller.initialize();
    }

    @Test
    void shouldChangeDirectoryWhenChoiceButtonClickAndDirectorySelected() {
        String expectedPath = "/home/user/test1";
        File file = new File(expectedPath);
        when(directoryChooserService.showDialog(null)).thenReturn(file);
        controller.onChoiceDestinationPathButtonClick(null);

        assertThat(pathProperty.get()).isEqualTo(expectedPath);
    }

    @Test
    void shouldNotChangeDirectoryWhenChoiceButtonClickAndDirectoryNotSelected() {
        when(directoryChooserService.showDialog(null)).thenReturn(null);
        controller.onChoiceDestinationPathButtonClick(null);

        assertThat(pathProperty.get()).isEqualTo(testPath);
    }

    @Test
    void shouldDeletePatternFromPatternsListWhenDeleteButtonClickAndPatternSelected() {
        patternsListView.getSelectionModel().select(1);
        controller.onDeletePatternButtonClick(null);

        verify(ruleService).deletePatternsFromPatternsList(patternsList,
                patternsListView.getSelectionModel().getSelectedIndices());
    }

    @Test
    void shouldNotDeletePatternFromPatternsListWhenDeleteButtonClickAndPatternNotSelected() {
        controller.onDeletePatternButtonClick(null);
        verify(ruleService, never()).deletePatternsFromPatternsList(any(), any());
    }

    @Test
    void shouldAddOnePatternToPatternsListWhenAddButtonClick() {
        controller.onAddPatternButtonClick(null);

        verify(ruleService).addPatternsToPatternsList(patternsList);
    }

    @Test
    void shouldChangePatternTextWhenPatternTextChangedInConfig() {
        patternsList.remove(0);

        assertThat(patternsListView.getItems()).isEqualTo(patternsList);
        assertThat(patternsListView.getItems()).containsExactly(pattern2);
    }

    @Test
    void shouldChangePatternInConfigWhenPatternTextChangedInView() {
        String pattern3 = "pattern3";
        patternsListView.getItems().add(pattern3);

        assertThat(patternsList).isEqualTo(patternsListView.getItems()).hasSize(3);
    }

    @Test
    void shouldChangeDestinationPathTextInTextFieldWhenPathChangedInConfig() {
        String expectedPath = "/home/user/test1";
        pathProperty.set(expectedPath);
        assertThat(destinationDirectoryField.getText()).isEqualTo(expectedPath);
    }

    @Test
    void shouldChangeDestinationPathTextInConfigWhenPathChangedInTextField() {
        String expectedPath = "/home/user/test1";
        destinationDirectoryField.setText(expectedPath);
        assertThat(pathProperty.get()).isEqualTo(expectedPath);
    }
}
