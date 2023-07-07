package com.optimatech.digitmall.Enum;

public enum Status {
    WAIT(1), FOREARM(2), DELIVERY(3), RECEIVED(4);
    //chờ xác nhận
    //đang chuẩn bị
    //đang giao
    //đã nhận

    Status(int i) {
    }
}
