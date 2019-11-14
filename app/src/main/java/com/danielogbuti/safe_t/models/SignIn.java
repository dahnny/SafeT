package com.danielogbuti.safe_t.models;

public class SignIn {

    private String SignedIn;
    private String Submitted;

    public SignIn(String signedIn, String submitted) {
        this.SignedIn = signedIn;
        this.Submitted = submitted;
    }

    public SignIn() {
    }

    public String getSignedIn() {
        return SignedIn;
    }

    public void setSignedIn(String signedIn) {
        SignedIn = signedIn;
    }

    public String getSubmitted() {
        return Submitted;
    }

    public void setSubmitted(String submitted) {
        Submitted = submitted;
    }
}
