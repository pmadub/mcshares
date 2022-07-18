package com.mcb.mcsharesproject.repositories.error;

import com.mcb.mcsharesproject.entities.error.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long> {
}
