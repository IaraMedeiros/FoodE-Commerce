package com.mycompany.entities;

public class CheckoutDTO {
        private User user;
        private Shipping shipping;
        private Payment payment;
        private String paymentMethod;

        private CardData cardData;
        private PixData pixData;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public CardData getCardData() {
        return cardData;
    }

    public void setCardData(CardData cardData) {
        this.cardData = cardData;
    }

    public PixData getPixData() {
        return pixData;
    }

    public void setPixData(PixData pixData) {
        this.pixData = pixData;
    }
}


