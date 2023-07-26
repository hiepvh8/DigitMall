package com.optimatech.digitmall.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.optimatech.digitmall.Enum.Payment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "billcode")
    private String billCode;
    @Column(name = "timeat")
    private LocalDateTime timeAt;
    private String total;
    @Enumerated(EnumType.STRING)
    private Payment payment; //  phương thức thanh toán
//    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<Product> products;
   // private List<Voucher> vouchers;
    @Column(name = "statusbill")
    private Boolean statusBill;
    private final String vat = "0.1"; // thuế vat
    private String disscount; // tổng tiền giảm
    @Column(name = "realmoney")
    private String realMoney; // giá tiền thực sự phải trả

    @ManyToOne
    @JoinColumn(name = "customerid", referencedColumnName = "id")
    @JsonBackReference
    private Customer customer;
}
