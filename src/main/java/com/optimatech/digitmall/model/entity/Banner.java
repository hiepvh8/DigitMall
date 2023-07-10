package com.optimatech.digitmall.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "banner")
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "bannername")
    private String bannerName;
    private LocalDateTime start;
    private LocalDateTime end;

    @ManyToOne // xóa 1 banner không ảnh hưởng đến seller , seller bị xóa sẽ kéo theo customer bị xóa
            // và mây mây thứ nữa sẽ bị xóa theo. nếu dùng case.TYPE_ALL ở đây thì câu chuyện sẽ rất tệ
    @JoinColumn(name = "sellerid", referencedColumnName = "id")
    @JsonBackReference
    private Seller seller;
}
