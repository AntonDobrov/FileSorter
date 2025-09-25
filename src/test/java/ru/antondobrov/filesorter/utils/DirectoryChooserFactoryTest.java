package ru.antondobrov.filesorter.utils;

import static org.assertj.core.api.Assertions.assertThat;
import javafx.stage.DirectoryChooser;
import org.junit.jupiter.api.Test;

class DirectoryChooserFactoryTest {
    @Test
    void shouldCreateDirectoryChooserInstance() {
        DirectoryChooserFactory factory = new DirectoryChooserFactory();

        assertThat(factory.create()).isExactlyInstanceOf(DirectoryChooser.class);
    }
}
