package com.Aviary.dao;

import java.util.List;

import com.Aviary.components.JDBIProvider;
import com.Aviary.components.ReviewView;

public class ReviewDao {
    public static List<ReviewView> getReviewOf(int productID){
        return JDBIProvider.get().withHandle(handle -> {
            return handle.createQuery("select r.ID, u.fullName, r.rating, r.comment, r.createdAt from Review r join UserDetails u on r.accID = u.accID where r.PID = :PID order by r.createdAt desc;")
            .bind("PID", productID)
            .mapToBean(ReviewView.class)
            .list();
        });
    }
}
