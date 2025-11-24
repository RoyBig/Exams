package com.example.exams.Model.Data.db;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.regex.Pattern;

@Getter
@Setter
@Embeddable
public class Email {

    @Column(name = "email", length = 40)
    private String value;

    public Email() {}

    public Email(String value) {
        this.value = value;
    }

    // getter dla JPA
    public String getValue() {
        return value;
    }

    // setter dla JPA
    public void setValue(String value) {
        this.value = value;
    }

    // wygodny getter dla kodu biznesowego
    public String get() {
        return value;
    }

    // wygodny setter dla kodu biznesowego
    public void set(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    // możesz też dodać walidację przy tworzeniu
    public static Email of(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        return new Email(email.trim());
    }
}


