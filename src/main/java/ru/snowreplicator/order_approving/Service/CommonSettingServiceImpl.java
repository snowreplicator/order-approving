package ru.snowreplicator.order_approving.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.snowreplicator.order_approving.Entity.CommonSetting;
import ru.snowreplicator.order_approving.Repository.CommonSettingRepository;
import ru.snowreplicator.order_approving.Service.Common.CommonSettingConst;
import ru.snowreplicator.order_approving.Service.Interface.CommonSettingService;
import ru.snowreplicator.order_approving.ViewModel.SettingsViewModel;
import ru.snowreplicator.order_approving.ViewModel.SettingsViewModel.LocaleSettingsViewModel;

@Service
@RequiredArgsConstructor
public class CommonSettingServiceImpl implements CommonSettingService {

    private final CommonSettingRepository commonSettingRepository;

    @Override
    public SettingsViewModel getSettings() {
        LocaleSettingsViewModel locales = new LocaleSettingsViewModel(
                getSetting(CommonSettingConst.defaultLocale, true),
                getSetting(CommonSettingConst.availableLocales, true));
        return new SettingsViewModel(locales);
    }

    @Override
    public String getSetting(String option, boolean useDefaultSetting) {
        CommonSetting commonSetting = commonSettingRepository.findById(option).orElse(null);
        if (useDefaultSetting && commonSetting == null) {
            commonSetting = CommonSetting.fillDefaultValue(option);
        }

        return commonSetting == null ? "" : commonSetting.getValue();
    }

}
