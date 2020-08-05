package com.me.callme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.callme.model.Sms;

@Repository
public interface SmsRepository extends JpaRepository<Sms, Integer> {

}
