package com.medical_area.private_area.common.repository;

import com.medical_area.private_area.common.model.Notifications;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Notifications, Integer> {

    boolean existsByIdentityAndUserId(String identity,Integer userId);

}