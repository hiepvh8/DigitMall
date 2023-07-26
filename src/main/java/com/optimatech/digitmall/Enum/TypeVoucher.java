package com.optimatech.digitmall.Enum;

public enum TypeVoucher {
    FREESHIP(1), //
    ALLSERVER(2), // toàn server sử dụng
    XTRA(3),// siêu cấp
    CATE(4), // giảm giá dựa trên ngành hàng VD ngành thời trang, ngành điện tử, ngành tạp hóa,...
    MARK(5), // giảm giá dựa trên thương hiệu VD Apple, Rolex,...
    ;

    TypeVoucher(int i) {
    }
}
