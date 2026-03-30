package com.automationexercise.front.pom.factory.model;

public class ContactUsFormData {
    private final String name;
    private final String email;
    private final String subject;
    private final String message;
    private final String fileName;

    public ContactUsFormData(String name, String email, String subject, String message, String fileName) {
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String getFileName() {
        return fileName;
    }
}
