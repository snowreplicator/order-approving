package ru.snowreplicator.order_approving.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.snowreplicator.order_approving.Entity.CommonSetting;

public interface CommonSettingRepository extends JpaRepository<CommonSetting, String> {
}
