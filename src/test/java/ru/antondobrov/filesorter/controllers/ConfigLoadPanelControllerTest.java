package ru.antondobrov.filesorter.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.antondobrov.filesorter.services.IConfigLoadService;
import ru.antondobrov.filesorter.services.IFileChooserService;

@ExtendWith(MockitoExtension.class)
public class ConfigLoadPanelControllerTest {

    @Mock
    private IConfigLoadService configLoadService;
    @Mock
    private IFileChooserService fileChooserService;
    @Mock
    private ActionEvent event;
    @Mock
    private Node node;
    @Mock
    private Scene scene;
    @Mock
    private Stage window;

    private File file;

    private ConfigLoadPanelController configLoadPanelController;

    @BeforeEach
    void setUp() {
        configLoadPanelController =
                new ConfigLoadPanelController(configLoadService, fileChooserService);
        when(event.getSource()).thenReturn(node);
        when(node.getScene()).thenReturn(scene);
        when(scene.getWindow()).thenReturn(window);

        file = new File("/home/user/test");
    }

    @Test
    void shouldSaveConfigWhenConfigFileIsChosen() {
        when(fileChooserService.showSaveDialog(any(Stage.class))).thenReturn(file);

        configLoadPanelController.onSaveConfigButtonClick(event);

        verify(configLoadService).saveConfig(file);
    }

    @Test
    void shouldNotSaveConfigWhenConfigFileIsNotChosen() {
        when(fileChooserService.showSaveDialog(any(Stage.class))).thenReturn(null);

        configLoadPanelController.onSaveConfigButtonClick(event);

        verify(configLoadService, never()).saveConfig(any(File.class));
    }

    @Test
    void shouldOpenConfigWhenConfigFileIsChosen() {
        when(fileChooserService.showOpenDialog(any(Stage.class))).thenReturn(file);

        configLoadPanelController.onOpenConfigButtonClick(event);

        verify(configLoadService).openConfig(file);
    }

    @Test
    void shouldNotOpenConfigWhenConfigFileIsNotChosen() {
        when(fileChooserService.showOpenDialog(any(Stage.class))).thenReturn(null);

        configLoadPanelController.onOpenConfigButtonClick(event);

        verify(configLoadService, never()).openConfig(any(File.class));
    }

}
