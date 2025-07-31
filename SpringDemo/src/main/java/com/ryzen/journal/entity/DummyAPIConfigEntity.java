package com.ryzen.journal.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dummy_api")
@Data
@NoArgsConstructor
public class DummyAPIConfigEntity {

    private String key;
    private String value;
}
