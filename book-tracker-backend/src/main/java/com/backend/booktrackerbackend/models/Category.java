package com.backend.booktrackerbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(generator = "categories")
    @TableGenerator(name = "categories", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="categories",
            initialValue=1, allocationSize=1)
    private Integer id;

    @Column(name = "name")
    private String name;
}
