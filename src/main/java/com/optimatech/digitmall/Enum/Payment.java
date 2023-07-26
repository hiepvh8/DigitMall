package com.optimatech.digitmall.Enum;

public enum Payment {
    BYCOIN(1),// thanh toán bằng xu
    BYWALLET(2), // thanh toán bằng ví điện tử
    BYCASH(3) // tiền mặt
    ;

    Payment(int i) {
    }
}
