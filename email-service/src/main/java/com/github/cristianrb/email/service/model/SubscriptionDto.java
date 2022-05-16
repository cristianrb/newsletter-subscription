package com.github.cristianrb.email.service.model;

public class SubscriptionDto {

    private String id;
    private String email;
    private String firstName;
    private String gender;
    private String dateOfBirth;
    private boolean consented;
    private String newsletterId;

    public SubscriptionDto(){}

    public SubscriptionDto(String id, String email, String firstName, String gender, String dateOfBirth, boolean consented, String newsletterId) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.consented = consented;
        this.newsletterId = newsletterId;
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
    public String toString() {
        return "SubscriptionDto{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", consented=" + consented +
                ", newsletterId='" + newsletterId + '\'' +
                '}';
    }
}
