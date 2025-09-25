package ru.antondobrov.filesorter.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.File;
import javafx.stage.DirectoryChooser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.antondobrov.filesorter.utils.DirectoryChooserFactory;
import ru.antondobrov.filesorter.utils.ILocalizer;

@ExtendWith(MockitoExtension.class)
class DirectoryChooserServiceTest {
    @Mock
    private ILocalizer localizer;

    @Mock
    private DirectoryChooserFactory factory;

    @Mock
    private DirectoryChooser directoryChooser;
    @Captor
    private ArgumentCaptor<File> initialDirectoryCaptor;

    private DirectoryChooserService directoryChooserService;

    @BeforeEach
    void setUp() {
        when(factory.create()).thenReturn(directoryChooser);
        directoryChooserService = new DirectoryChooserService(localizer, factory);
    }

    @Test
    void shouldSetCorrectTitleForOpenDialog() {
        String titleKey = "start.directory.choose.dialog.title";
        String expectedTitle = "Open start directory";
        when(localizer.get(titleKey)).thenReturn(expectedTitle);

        directoryChooserService.showDialog(null);

        verify(directoryChooser).setTitle(expectedTitle);
    }

    @Test
    void shouldSetUserHomeAsInitialDirectoryForOpenDialog() {
        String expectedUserHome = System.getProperty("user.home");

        directoryChooserService.showDialog(null);

        verify(directoryChooser).setInitialDirectory(initialDirectoryCaptor.capture());
        File actualDirectory = initialDirectoryCaptor.getValue();

        assertThat(actualDirectory.getPath()).isEqualTo(expectedUserHome);
    }
}
