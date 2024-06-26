package com.backend.poll_website.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "survey")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question", nullable = false)
    private String question;

    @JsonIgnore
    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @JsonIgnore
    @Column(name = "deletion_time")
    private LocalDateTime deletionTime;

    @JsonIgnore
    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;
}
