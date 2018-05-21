package com.medical_area.private_area.chat.repository;

import com.medical_area.private_area.chat.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatRepository extends CrudRepository<Message, Integer> {

    List<Message> findAllByPatientIdAndDoctorId(Integer patientId, Integer doctorId);

    List<Message> findAllByDoctorIdAndSeen(Integer doctorId, boolean seen);

    List<Message> findAllByPatientId(Integer doctorId);

    List<Message> findAllByDoctorId(Integer doctorId);
}
