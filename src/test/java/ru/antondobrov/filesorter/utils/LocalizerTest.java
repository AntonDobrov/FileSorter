package ru.antondobrov.filesorter.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.util.ResourceBundle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LocalizerTest {
    @Mock
    ResourceBundle bundle;

    private Localizer localizer;
    private String key;

    @BeforeEach
    void setUp() {
        key = "t.e.s.t";
    }

    @Test
    void shouldReturnLocalizedStringWhenKeyIsValid() {
        when(bundle.containsKey(key)).thenReturn(true);
        String localizedString = "test";
        when(bundle.getString(key)).thenReturn(localizedString);

        localizer = new Localizer(bundle);

        assertThat(localizer.get(key)).isEqualTo(localizedString);
    }

    @Test
    void shouldReturnKeyStringWhenKeyIsInvalid() {
        when(bundle.containsKey(key)).thenReturn(false);
        localizer = new Localizer(bundle);

        assertThat(localizer.get(key)).isEqualTo(key);
    }

    @Test
    void shouldReturnKeyStringWhenBundleIsNull() {
        localizer = new Localizer(null);

        assertThat(localizer.get(key)).isEqualTo(key);
    }

    @Test
    void shouldSetAndGetBundle() {
        when(bundle.containsKey(key)).thenReturn(true);

        localizer = new Localizer(null);
        assertThat(localizer.getBundle()).isNull();
        localizer.setBundle(bundle);

        assertThat(localizer.getBundle().containsKey(key)).isEqualTo(bundle.containsKey(key));
    }

    @Test
    void shouldChangeBehaviorAfterSettingNewBundle() {
        localizer = new Localizer(null);
        assertThat(localizer.get(key)).isEqualTo(key);

        String localizedString = "test";
        when(bundle.containsKey(key)).thenReturn(true);
        when(bundle.getString(key)).thenReturn(localizedString);
        localizer.setBundle(bundle);

        assertThat(localizer.get(key)).isEqualTo(localizedString);
    }
}
