package com.bordify.auth.domain;

public interface SecurityService {

    public String encode(String textToEncode);

    public Boolean matches(String textToEncode, String encodedText);

}
