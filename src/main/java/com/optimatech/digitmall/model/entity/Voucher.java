package com.optimatech.digitmall.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.optimatech.digitmall.Enum.TypeVoucher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "voucher")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vouchername")
    private String voucherName;
    @Column(name = "reductionrate")
    private String reductionRate; // phầm trăm giảm
    @Column(name = "reductionprice")
    private String reductionPrice; // giảm trực tiếp vào giá
    @Column(name = "reductionminimum")
    private String reductionMinimum; // giảm khi đơn hàng đạt tối thiểu xxx đồng
    @Column(name = "maximumfreeship")
    private String maximumFreeShip; // tiền giảm tối đa khi dùng free ship

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryid", referencedColumnName = "id")
    @JsonBackReference
    private Category category; // ngành hàng được phép áp voucher
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trademarkid", referencedColumnName = "id")
    @JsonBackReference
    private Trademark trademark; // thương hiệu được áp voucher
    private TypeVoucher type;

}
