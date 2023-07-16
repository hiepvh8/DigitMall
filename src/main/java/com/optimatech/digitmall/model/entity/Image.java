package com.optimatech.digitmall.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.optimatech.digitmall.Enum.TypeImage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
//    @Enumerated(EnumType.STRING)
//    private TypeImage type;

    @ManyToOne
    @JoinColumn(name = "productid", referencedColumnName = "id")
    @JsonBackReference
    private Product product;

    @ManyToOne
    @JoinColumn(name = "commentid", referencedColumnName = "id")
    @JsonBackReference
    private Comment comment;

}
