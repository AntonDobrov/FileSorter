package ru.antondobrov.filesorter.utils;

import java.util.ResourceBundle;

/**
 * A concrete implementation of the {@link ILocalizer} interface that uses a standard Java
 * {@link ResourceBundle} to retrieve localized strings.
 * <p>
 * This class acts as a wrapper around a {@code ResourceBundle}, providing a consistent way to
 * access translations throughout the application. It includes safe-handling logic: if the bundle is
 * not set or a key is not found, it returns the key itself as a fallback, which helps in
 * identifying missing translations during development.
 */
public class Localizer implements ILocalizer {

    private ResourceBundle bundle;

    /**
     * Constructs a new Localizer instance with the given resource bundle. Note: This constructor
     * has package-private access.
     *
     * @param bundle The resource bundle to be used for string lookups. Can be {@code null}.
     */
    Localizer(ResourceBundle bundle) {
        this.setBundle(bundle);
    }

    /**
     * Retrieves the localized string for the specified key from the underlying resource bundle.
     * <p>
     * This implementation is safe: if the resource bundle has not been set (is {@code null}), or if
     * the bundle does not contain the specified key, the method will return the key itself as a
     * fallback.
     *
     * @param key The identifier for the desired string.
     * @return The localized string, or the key itself if the translation is not found or the bundle
     *         is not configured.
     */
    @Override
    public String get(String key) {
        if (bundle == null) {
            return key;
        } else {
            return bundle.containsKey(key) ? bundle.getString(key) : key;
        }
    }

    /**
     * Sets or replaces the resource bundle used by this localizer.
     * <p>
     * This allows for dynamic language changes at runtime without needing to re-instantiate the
     * localizer.
     *
     * @param sourceBundle The new resource bundle to use. Can be {@code null}.
     */
    public void setBundle(ResourceBundle sourceBundle) {
        bundle = sourceBundle;
    }

    /**
     * Returns the underlying {@link ResourceBundle} currently used by this localizer.
     *
     * @return The current resource bundle, which may be {@code null}.
     */
    public ResourceBundle getBundle() {
        return bundle;
    }
}
