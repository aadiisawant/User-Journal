package com.ryzen.journal.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_db")
@Data
public class JournalEntity {
    @org.springframework.data.annotation.Id
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;

}
