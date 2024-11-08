package ru.snowreplicator.order_approving.ViewModel;

public record SettingsViewModel(LocaleSettingsViewModel locales) {

    public record LocaleSettingsViewModel(String defaultLocale, String availableLocales) {
    }

}
