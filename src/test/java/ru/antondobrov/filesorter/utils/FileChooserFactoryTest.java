package ru.antondobrov.filesorter.utils;

import static org.assertj.core.api.Assertions.assertThat;
import javafx.stage.FileChooser;
import org.junit.jupiter.api.Test;

class FileChooserFactoryTest {
    @Test
    void shouldCreateFileChooserInstance() {
        FileChooserFactory factory = new FileChooserFactory();

        assertThat(factory.create()).isExactlyInstanceOf(FileChooser.class);
    }
}

