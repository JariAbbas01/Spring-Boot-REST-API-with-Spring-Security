package com.practiceapp.practiceapp.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

@Document(collection = "JournalEntry")
@Data
@Getter
@Setter
@NoArgsConstructor
public class JournalEntry {

    @Id
    private ObjectId id;

    @NonNull
    private String title;

    private String content;

    private LocalDateTime date;

}
