package com.mycompany.entities.enums;

import jakarta.persistence.Table;

@Table(name="order_status")
public enum OrderStatus {
    WAITING_PAYMENT(0),
    PAID(1),
    SHIPPED(2),
    DELIVERED(3),
    CANCELED(4);

    private int code;

    OrderStatus(int code) {

        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static OrderStatus                                                                                                     valueOf(int code) throws IllegalAccessException {
        for( OrderStatus value : OrderStatus.values()){
            if (value.getCode() == code){
                return value;
            }
        }
        throw new IllegalAccessException("Esse orderstatus é invalido");
    }
}

