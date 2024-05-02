package com.mycompany.entities;

import jakarta.persistence.Entity;

@Entity
public class PixData extends PaymentData {
    private String chave_pix;

    public PixData() {
    }

    public String getChave_pix() {
        return chave_pix;
    }

    public void setChave_pix(String chave_pix) {
        this.chave_pix = chave_pix;
    }
}