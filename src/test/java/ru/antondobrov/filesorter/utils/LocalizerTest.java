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
public class LocalizerTest {
    @Mock
    ResourceBundle bundle;

    private Localizer localizer;
    private String key;
    private String localizedString;

    @BeforeEach
    void setUp() {
        key = "a.a.a";
        localizedString = "aaa";
    }

    @Test
    void shouldReturnLocalizedStringFromGetWhenKeyIsValid() {

        when(bundle.containsKey(key)).thenReturn(true);
        when(bundle.getString(key)).thenReturn(localizedString);
        localizer = new Localizer(bundle);

        assertThat(localizer.get(key)).isEqualTo(localizedString);
    }

    @Test
    void shouldReturnKeyStringFromGetWhenKeyIsInvalid() {
        when(bundle.containsKey(key)).thenReturn(false);
        localizer = new Localizer(bundle);

        assertThat(localizer.get(key)).isEqualTo(key);
    }

    @Test
    void shouldReturnKeyStringFromGetWhenBundleIsNull() {
        localizer = new Localizer(null);

        assertThat(localizer.get(key)).isEqualTo(key);
    }


    @Test
    void shouldSetAndGetBundle() {
        localizer = new Localizer(null);

        localizer.setBundle(bundle);

        assertThat(localizer.getBundle()).isEqualTo(bundle);
    }

    @Test
    void shouldChangeBehaviorAfterSettingNewBundle() {
        localizer = new Localizer(null);
        assertThat(localizer.get(key)).isEqualTo(key);

        when(bundle.containsKey(key)).thenReturn(true);
        when(bundle.getString(key)).thenReturn(localizedString);
        localizer.setBundle(bundle);

        assertThat(localizer.get(key)).isEqualTo(localizedString);
    }
}
