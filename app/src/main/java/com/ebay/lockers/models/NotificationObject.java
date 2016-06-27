package com.ebay.lockers.models;

/**
 * Created by Sunil on 6/23/2016.
 */
public class NotificationObject {

    private String user;
    private String transaction;
    private String offer;
    private String itemInterested;

    public NotificationObject(String user, String transaction, String offer, String itemInterested) {
        this.user = user;
        this.transaction = transaction;
        this.offer = offer;
        this.itemInterested = itemInterested;
    }

    public String getUser() {
        return user;
    }

    public String getTransaction() {
        return transaction;
    }

    public String getOffer() {
        return offer;
    }

    public String getItemInterested() {
        return itemInterested;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public void setItemInterested(String itemInterested) {
        this.itemInterested = itemInterested;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(user);

        switch (transaction) {
            case "buy":
                sb.append(" has offered you ").append(offer)
                        .append(" for your locker item: ").append(itemInterested);
                break;

            case "exchange":
                sb.append(" has offered to exchange his item: ").append(offer)
                        .append(" for your locker item: ").append(itemInterested);
                break;
        }
        return sb.toString();
    }
}
