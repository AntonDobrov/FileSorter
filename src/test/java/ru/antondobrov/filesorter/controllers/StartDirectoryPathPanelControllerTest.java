package ru.antondobrov.filesorter.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.File;
import java.lang.reflect.Field;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.framework.junit5.ApplicationExtension;
import ru.antondobrov.filesorter.services.IDirectoryChooserService;
import ru.antondobrov.filesorter.services.IStartDirectoryHandler;

@ExtendWith(ApplicationExtension.class)
@ExtendWith(MockitoExtension.class)
public class StartDirectoryPathPanelControllerTest {

    @Mock
    private IStartDirectoryHandler startDirectoryHandler;
    @Mock
    private IDirectoryChooserService directoryChooserService;
    @Mock
    private ActionEvent event;
    @Mock
    private Node node;
    @Mock
    private Scene scene;
    @Mock
    private Stage window;

    File file;

    @Captor
    private ArgumentCaptor<File> startDirectoryCaptor;

    private TextField textField;

    private StartDirectoryPathPanelController startDirectoryPathPanelController;

    @BeforeEach
    void setUp() throws Exception {
        startDirectoryPathPanelController = new StartDirectoryPathPanelController(
                startDirectoryHandler, directoryChooserService);

        textField = new TextField();

        Field textField_field = StartDirectoryPathPanelController.class
                .getDeclaredField("startDirectoryPathTextField");
        textField_field.setAccessible(true);
        textField_field.set(startDirectoryPathPanelController, textField);

        startDirectoryPathPanelController.initialize();

        file = new File("/home/user/test");
    }

    @Test
    void shouldChangePathInTextFieldWhenUserIsChoiceStartDirectory() {
        when(event.getSource()).thenReturn(node);
        when(node.getScene()).thenReturn(scene);
        when(scene.getWindow()).thenReturn(window);
        when(directoryChooserService.showDialog(any(Stage.class))).thenReturn(file);

        startDirectoryPathPanelController.onChoiceStartDirectoryButtonClick(event);

        assertThat(textField.getText()).isEqualTo(file.getAbsolutePath());
    }

    @Test
    void shouldNotChangePathInTextFieldWhenUserIsNotChoiceStartDirectory() {
        when(event.getSource()).thenReturn(node);
        when(node.getScene()).thenReturn(scene);
        when(scene.getWindow()).thenReturn(window);
        when(directoryChooserService.showDialog(any(Stage.class))).thenReturn(null);

        startDirectoryPathPanelController.onChoiceStartDirectoryButtonClick(event);

        assertThat(textField.getText()).isEmpty();
    }

    @Test
    void shouldCallHandlerWithNewFileWhenTextIsSet() {
        String path1 = "/home/user/test1";
        String path2 = "/home/user/test";

        textField.setText(path1);

        textField.setText(path2);

        verify(startDirectoryHandler, times(2))
                .handleStartDirectory(startDirectoryCaptor.capture());
        File actualDirectory = startDirectoryCaptor.getValue();

        assertThat(actualDirectory.getPath()).isEqualTo(path2);
    }

    @Test
    void shouldCallHandlerWithNullWhenTextIsCleared() {
        String path1 = "/home/user/test1";
        String path2 = "";

        textField.setText(path1);

        textField.setText(path2);

        verify(startDirectoryHandler).handleStartDirectory(null);


    }
}
