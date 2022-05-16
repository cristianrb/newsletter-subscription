package com.github.cristianrb.subscriptionservice.model;

import java.util.Objects;

public class Subscription {

    private String id;
    private String email;
    private String firstName;
    private String gender;
    private String dateOfBirth;
    private boolean consented;
    private String newsletterId;

    public Subscription(String id, String email, String firstName, String gender, String dateOfBirth, boolean consented, String newsletterId) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.consented = consented;
        this.newsletterId = newsletterId;
    }

    public SubscriptionDto toSubscriptionDto() {
        return new SubscriptionDto(this.id, this.email, this.firstName, this.gender, this.dateOfBirth, this.consented, this.newsletterId);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isConsented() {
        return consented;
    }

    public void setConsented(boolean consented) {
        this.consented = consented;
    }

    public String getNewsletterId() {
        return newsletterId;
    }

    public void setNewsletterId(String newsletterId) {
        this.newsletterId = newsletterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subscription)) return false;
        Subscription that = (Subscription) o;
        return consented == that.consented && Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(firstName, that.firstName) && Objects.equals(gender, that.gender) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(newsletterId, that.newsletterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, firstName, gender, dateOfBirth, consented, newsletterId);
    }
}
