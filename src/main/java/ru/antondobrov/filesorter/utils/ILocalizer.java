package ru.antondobrov.filesorter.utils;

/**
 * Defines a contract for a service that provides localized string resources.
 * <p>
 * This interface abstracts the mechanism of retrieving translated text, allowing different
 * localization strategies (e.g., reading from resource bundles, databases, or external files) to be
 * implemented and used interchangeably throughout the application.
 */
public interface ILocalizer {

    /**
     * Retrieves the localized string associated with the specified key.
     * <p>
     * If a translation for the given key cannot be found, implementations should ideally return a
     * noticeable placeholder, such as the key itself (e.g., '{@code error.file.not.found}'), to aid
     * in identifying missing resources during development.
     *
     * @param key The unique identifier for the desired string resource.
     * @return The localized string for the given key, or a placeholder if the key is not found.
     */
    String get(String key);
}
