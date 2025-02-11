package com.jjim.user.infra.encrypt;

public interface PasswordEncoder {

    String encode(String password);

    boolean verifyPassword(String requestPassword, String userPassword);

}
