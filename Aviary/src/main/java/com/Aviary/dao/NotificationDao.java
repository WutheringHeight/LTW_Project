package com.Aviary.dao;

import java.util.List;

import com.Aviary.components.JDBIProvider;
import com.Aviary.components.Notification;

public class NotificationDao {
    public static void createNotification(int accountID, String message){
        JDBIProvider.get().useHandle(handle -> {
            handle.createUpdate("insert into Notification (accID, message, isRead, createdAt) values(:accID, :message, 0, getdate())")
            .bind("accID",accountID)
            .bind("message",message)
            .execute();
        });
    }
    public static int getUnreadNotificationCount(int accountID){
        return JDBIProvider.get().withHandle(handle -> {
            return handle.createQuery("select count(*) from Notification where accID = :accID and isRead = 0;")
            .bind("accID",accountID)
            .mapToBean(Integer.class)
            .one();
        });
    }
    public static List<Notification> getNotification(int accountID){
        return JDBIProvider.get().withHandle(handle -> {
            return handle.createQuery("select ID, message, isRead, createdAt from Notification where accID = :accID order by createdAt desc;")
            .bind("accID",accountID)
            .mapToBean(Notification.class)
            .list();
        });
    }
    public static void markAsRead(int notifID){
        JDBIProvider.get().useHandle(handle -> {
            handle.createUpdate("update Notification set isRead = 1 where ID = :notif_ID;")
            .bind("notif_ID",notifID)
            .execute();
        });
    }
    

}
