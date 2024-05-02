package com.mycompany.enums;

public enum PaymentMethod {
    CARTAO_CREDITO(1),
    CARTAO_DEBITO(2),
    DINHEIRO(3),
    PIX(4),
    VOUCHER(5);

    private int code;

    PaymentMethod(int code) {

        this.code = code;
    }

    public int getCode(){
        return code;
    }


}
