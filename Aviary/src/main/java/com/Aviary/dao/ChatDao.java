package com.Aviary.dao;

import java.util.List;

import com.Aviary.components.ChatMessage;
import com.Aviary.components.JDBIProvider;

public class ChatDao {
    //return a list of account ID
    public static List<Integer> getChats(int accountID){
        return JDBIProvider.get().withHandle(handle -> {
            return handle.createQuery("select distinct case when sender_accID <> :accID then sender_accID else receiver_accID end as chatter, max(sentAt) AS lastMessageTime from ChatMessage where sender_accID = :accID or receiver_accID = :accID group by case when sender_accID <> :accID then sender_accID else receiver_accID end order by createdAt desc;")
            .bind("accID",accountID)
            .map((rs,ctx) -> rs.getInt("chatter"))
            .list();
        });
    }

    public static List<ChatMessage> getChatHistory(int accountID, int otherAccountID){
        return JDBIProvider.get().withHandle(handle -> {
            return handle.createQuery("select * from ChatMessage where (sender_accID = :accID and receiver_accID = :other_accID) or (receiver_accID = :accID and sender_accID = :other_accID) order by sentAt desc;")
            .bind("accID", accountID)
            .bind("other_accID", otherAccountID)
            .mapToBean(ChatMessage.class)
            .list();
        });
    }
    public static void sendMessage(int accountID,int otherAccountID, String message){
        JDBIProvider.get().useHandle(handle -> {
            handle.createUpdate("insert into ChatMessage (sender_accID, receiver_accID, message) values(:accID, :other_accID, :message)")
            .bind("accID", accountID)
            .bind("other_accID", otherAccountID)
            .bind("message", message)
            .execute();
        });
    }
    public static void markAsRead(int messaegID){
        JDBIProvider.get().useHandle(handle -> {
            handle.createUpdate("update ChatMessage set isRead = 1 where ID = :msg_ID;")
            .bind("msg_ID", messaegID)
            .execute();
        });
    }
}
