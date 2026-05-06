package com.nttdata.desafio.repository;

import com.nttdata.desafio.entity.AddressLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressLogRepository extends JpaRepository<AddressLog, Long> {
}
