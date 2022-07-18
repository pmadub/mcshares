package com.mcb.mcsharesproject.entities.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "ERROR_LOG")
@NoArgsConstructor
@Getter
@Setter
public class ErrorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "TIMESTAMP_OCCURRED")
    private LocalDateTime timestamp;

    @Column(name = "REQUEST")
    private String request;

    @Column(name = "EXCEPTION")
    private String exceptionMessage;

    @Column(name = "FILE_NAME")
    private String fileName;

}
