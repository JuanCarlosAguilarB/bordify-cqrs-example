package com.bordify.user.domain;

public interface SecurityService {

    public String encode(String textToEncode);
    public Boolean matches(String textToEncode, String encodedText);

}
