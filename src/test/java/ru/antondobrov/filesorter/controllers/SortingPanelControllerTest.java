package ru.antondobrov.filesorter.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.lang.reflect.Field;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.ProgressBar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.framework.junit5.ApplicationExtension;
import ru.antondobrov.filesorter.services.ISorterConfig;
import ru.antondobrov.filesorter.services.ISorterService;

@ExtendWith(ApplicationExtension.class)
@ExtendWith(MockitoExtension.class)
class SortingPanelControllerTest {
    @Mock
    ISorterConfig config;
    @Mock
    ISorterService service;

    ProgressBar progressBar;

    SortingPanelController controller;

    DoubleProperty progressProperty;

    @BeforeEach
    void setUp() throws Exception {
        progressProperty = new SimpleDoubleProperty();
        progressProperty.set(0);
        when(config.getSortingProgressProperty()).thenReturn(progressProperty);


        controller = new SortingPanelController(service, config);
        progressBar = new ProgressBar();
        Field progressBarFromController =
                SortingPanelController.class.getDeclaredField("sortingProgressBar");
        progressBarFromController.setAccessible(true);
        progressBarFromController.set(controller, progressBar);

        controller.initialize();
    }

    @Test
    void shouldStartSortingWHenStartSortingButtonIsClicked() {
        controller.onStartSortingButtonClick(null);

        verify(service).startSorting();
    }

    @Test
    void shouldChangeValueOnProgressBarIfValueChangedInConfig() {
        Double newProgress = 12.42;
        progressProperty.set(newProgress);

        assertThat(progressBar.progressProperty().get()).isEqualTo(newProgress);
    }
}
