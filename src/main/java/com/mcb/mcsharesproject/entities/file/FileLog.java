package com.mcb.mcsharesproject.entities.file;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name = "FILE_LOG")
@Getter
@Setter
public class FileLog implements Serializable {

    @Serial
    private static final long serialVersionUID = 4212092772670427122L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "NAME")
    private String fileName;

    @Column(name = "DATE_OF_ENTRY")
    private LocalDateTime dateOfEntry;

    @Column(name = "LOCATION")
    private String fileLocation;

    @Column(name = "CONTAIN_ERRORS")
    private boolean containErrors;


}
