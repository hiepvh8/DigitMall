package com.optimatech.digitmall.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "keywords")
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String keyword;
    private Long count; // số lần xuất hiện
    @Column(name = "timesearch")
    private LocalDateTime timeSearch; // thời gian gửi request tìm kiếm
}
