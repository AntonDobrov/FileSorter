package ru.antondobrov.filesorter.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.io.File;
import java.lang.reflect.Field;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.framework.junit5.ApplicationExtension;

@ExtendWith(ApplicationExtension.class)
@ExtendWith(MockitoExtension.class)
class StartDirectoryPathPanelControllerTest {

    @Mock
    private IStartDirectoryConfig config;
    @Mock
    private IDirectoryChooserService directoryChooserService;
    @Mock
    private ActionEvent event;

    StringProperty property;

    File file;

    TextField textField;

    StartDirectoryPathPanelController startDirectoryPathPanelController;

    @BeforeEach
    void setUp() throws Exception {
        startDirectoryPathPanelController =
                new StartDirectoryPathPanelController(config, directoryChooserService);
        property = new SimpleStringProperty();

        when(config.getStartDirectoryPathProperty()).thenReturn(property);

        textField = new TextField();

        Field textFieldFromController = StartDirectoryPathPanelController.class
                .getDeclaredField("startDirectoryPathTextField");
        textFieldFromController.setAccessible(true);
        textFieldFromController.set(startDirectoryPathPanelController, textField);

        startDirectoryPathPanelController.initialize();

        file = new File("/home/user/test");
    }

    @Test
    void shouldChangePathInTextFieldWhenUserIsChoiceStartDirectory() {
        when(directoryChooserService.showDialog(any(ActionEvent.class))).thenReturn(file);

        startDirectoryPathPanelController.onChoiceStartDirectoryButtonClick(event);

        assertThat(textField.getText()).isEqualTo(file.getAbsolutePath());
    }

    @Test
    void shouldNotChangePathInTextFieldWhenUserIsNotChoiceStartDirectory() {
        when(directoryChooserService.showDialog(any(ActionEvent.class))).thenReturn(null);
        String expectedText = "/home/user/test";
        textField.setText(expectedText);

        startDirectoryPathPanelController.onChoiceStartDirectoryButtonClick(event);

        assertThat(textField.getText()).isEqualTo(expectedText);
    }

    @Test
    void shouldChangePathInConfigWhenNewPathIsSet() {
        String path = "/home/user/test";

        textField.setText(path);

        assertThat(config.getStartDirectoryPathProperty().get()).isEqualTo(path);
    }

    @Test
    void shouldChangePathInTextFieldWhenPathInConfigIsChange() {
        String path = "/home/user/test";
        config.getStartDirectoryPathProperty().set(path);
        assertThat(textField.getText()).isEqualTo(path);
    }
}
