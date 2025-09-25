package ru.antondobrov.filesorter.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.File;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.antondobrov.filesorter.utils.FileChooserFactory;
import ru.antondobrov.filesorter.utils.ILocalizer;

@ExtendWith(MockitoExtension.class)
class FileChooserServiceTest {

    @Mock
    private ILocalizer localizer;

    @Mock
    private FileChooserFactory factory;

    @Mock
    private FileChooser fileChooser;

    @Captor
    private ArgumentCaptor<File> initialDirectoryCaptor;
    @Captor
    private ArgumentCaptor<FileChooser.ExtensionFilter> extensionFilterCaptor;

    private FileChooserService fileChooserService;

    @Mock
    private ActionEvent event;
    @Mock
    private Node node;
    @Mock
    private Scene scene;
    @Mock
    private Stage window;

    @BeforeEach
    void setUp() {
        when(factory.create()).thenReturn(fileChooser);
        fileChooserService = new FileChooserService(localizer, factory);
        when(event.getSource()).thenReturn(node);
        when(node.getScene()).thenReturn(scene);
        when(scene.getWindow()).thenReturn(window);
    }

    @Test
    void shouldSetCorrectTitleForOpenDialog() {
        String titleKey = "open.config.file.dialog.title";
        String expectedTitle = "Open configuration";
        when(localizer.get(titleKey)).thenReturn(expectedTitle);

        fileChooserService.showOpenDialog(event);

        verify(fileChooser).setTitle(expectedTitle);
    }

    @Test
    void shouldSetUserHomeAsInitialDirectoryForOpenDialog() {
        String expectedUserHome = System.getProperty("user.home");

        fileChooserService.showOpenDialog(event);

        verify(fileChooser).setInitialDirectory(initialDirectoryCaptor.capture());
        File actualDirectory = initialDirectoryCaptor.getValue();

        assertThat(actualDirectory.getPath()).isEqualTo(expectedUserHome);
    }

    @Test
    void shouldSetCorrectTitleForSaveDialog() {
        String nameKey = "config.initial.name";
        when(localizer.get(nameKey)).thenReturn("test");

        String descriptionKey = "json.files.extension.description";
        when(localizer.get(descriptionKey)).thenReturn("test");

        String titleKey = "save.config.file.dialog.title";
        String expectedTitle = "Save configuration";
        when(localizer.get(titleKey)).thenReturn(expectedTitle);

        ObservableList<FileChooser.ExtensionFilter> actualFilters =
                FXCollections.observableArrayList();
        when(fileChooser.getExtensionFilters()).thenReturn(actualFilters);

        fileChooserService.showSaveDialog(event);

        verify(fileChooser).setTitle(expectedTitle);
    }

    @Test
    void shouldSetCorrectInitialFileNameForSaveDialog() {
        String titleKey = "save.config.file.dialog.title";
        when(localizer.get(titleKey)).thenReturn("test");
        String descriptionKey = "json.files.extension.description";
        when(localizer.get(descriptionKey)).thenReturn("test");

        String nameKey = "config.initial.name";
        String expectedInitialName = "config.json";
        when(localizer.get(nameKey)).thenReturn(expectedInitialName);

        ObservableList<FileChooser.ExtensionFilter> actualFilters =
                FXCollections.observableArrayList();
        when(fileChooser.getExtensionFilters()).thenReturn(actualFilters);

        fileChooserService.showSaveDialog(event);

        verify(localizer).get(nameKey);
        verify(fileChooser).setInitialFileName(expectedInitialName);
    }

    @Test
    void shouldAddCorrectJsonExtensionFilterForSaveDialog() {
        String titleKey = "save.config.file.dialog.title";
        when(localizer.get(titleKey)).thenReturn("test");
        String nameKey = "config.initial.name";
        when(localizer.get(nameKey)).thenReturn("test");
        String descriptionKey = "json.files.extension.description";
        String expectedDescription = "JSON File (*.json)";
        String expectedExtension = "*.json";
        when(localizer.get(descriptionKey)).thenReturn(expectedDescription);

        ObservableList<FileChooser.ExtensionFilter> actualFilters =
                FXCollections.observableArrayList();
        when(fileChooser.getExtensionFilters()).thenReturn(actualFilters);

        fileChooserService.showSaveDialog(event);

        verify(localizer).get(descriptionKey);

        FileChooser.ExtensionFilter addedFilter = actualFilters.get(0);
        assertThat(addedFilter.getDescription()).isEqualTo(expectedDescription);

        List<String> extensions = addedFilter.getExtensions();
        assertThat(extensions).hasSize(1).containsExactly(expectedExtension);
    }

    @Test
    void shouldSetUserHomeAsInitialDirectoryForSaveDialog() {
        String titleKey = "save.config.file.dialog.title";
        when(localizer.get(titleKey)).thenReturn("test");
        String nameKey = "config.initial.name";
        when(localizer.get(nameKey)).thenReturn("test");
        String descriptionKey = "json.files.extension.description";
        when(localizer.get(descriptionKey)).thenReturn("test");

        ObservableList<FileChooser.ExtensionFilter> actualFilters =
                FXCollections.observableArrayList();
        when(fileChooser.getExtensionFilters()).thenReturn(actualFilters);

        String expectedUserHome = System.getProperty("user.home");

        fileChooserService.showSaveDialog(event);

        verify(fileChooser).setInitialDirectory(initialDirectoryCaptor.capture());
        File actualDirectory = initialDirectoryCaptor.getValue();

        assertThat(actualDirectory.getPath()).isEqualTo(expectedUserHome);
    }
}
