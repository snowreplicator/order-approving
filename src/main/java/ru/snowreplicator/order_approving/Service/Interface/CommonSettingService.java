package ru.snowreplicator.order_approving.Service.Interface;

import ru.snowreplicator.order_approving.ViewModel.SettingsViewModel;

public interface CommonSettingService {

    SettingsViewModel getSettings();

    String getSetting(String option, boolean useDefaultSetting);

}
