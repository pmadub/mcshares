package com.mcb.mcsharesproject.repositories.file;

import com.mcb.mcsharesproject.entities.file.FileLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileLogRepository extends JpaRepository<FileLog, Long> {
}
