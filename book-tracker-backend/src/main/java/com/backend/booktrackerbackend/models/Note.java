package com.backend.booktrackerbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "notes")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Note {
    @Id
    @GeneratedValue(generator = "notes")
    @TableGenerator(name = "notes", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="notes",
            initialValue=1, allocationSize=1)
    private Integer id;

    @Column(name = "tittle")
    private String tittle;

    @Column(name = "date")
    private Date date;

    @Column(name = "body")
    private String body;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
