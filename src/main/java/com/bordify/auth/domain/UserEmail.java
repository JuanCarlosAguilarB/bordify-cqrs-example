package com.bordify.auth.domain;

import lombok.SneakyThrows;

import java.util.regex.Pattern;

public class UserEmail {

    private String value;

    public UserEmail(String value) throws UserEmailException {
        ensureEmailIsValid(value);
        this.value = value;
    }

    public String value() {
        return value;
    }


    /**
     * Checks if the given email address is valid.
     *
     * @param email The email address to validate.
     * @throws IllegalArgumentException if the email address is not valid.
     */

    //    When dealing with multiple checked exceptions,
    //    we can use @SneakyThrows for simplify our code by eliminating the need for explicit exception handling in every method.
    @SneakyThrows
    public void ensureEmailIsValid(String email) throws UserEmailException {

        String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        boolean isValid = pattern.matcher(email).matches();

        if (!isValid) throw new UserEmailException("Email is not valid");

    }

}
