package com.Aviary.dao;

import com.Aviary.components.JDBIProvider;
import com.Aviary.components.UserSetting;

public class UserSettingDao {
    public static UserSetting getUserSetting(int accID) {
        return JDBIProvider.get().withHandle(handle ->
            handle.createQuery(
                    "select * from UserSettings where accID = :accID")
                .bind("accID", accID)
                .mapToBean(UserSetting.class)
                .findOne()
                .orElse(null)
        );
    }
    public static void updateUserSetting(UserSetting settings) {
        JDBIProvider.get().useHandle(handle -> {
            handle.createUpdate(
                    "update UserSettings set order_update_notif = :orderUpdateNotif, promo_notif = :promoNotif, send_email = :sendEmail, send_sms = :sendSms, language = :language, remember_me = :rememberMe, updatedAt = getdate() where accID = :accID")
                .bindBean(settings)
                .execute();
        });
    }
    public static int insertUserSetting(int accID) {
        return JDBIProvider.get().withHandle(handle ->
            handle.createUpdate(
                    "insert into UserSettings (accID) values (:accID)")
                .bind("accID", accID)
                .executeAndReturnGeneratedKeys("ID")
                .mapTo(Integer.class)
                .one()
        );
    }
}
