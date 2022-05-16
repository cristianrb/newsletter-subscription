package com.github.cristianrb.email.service.service;

import org.springframework.stereotype.Service;

public interface EmailService {

    void sendEmail(String email, String newsletterId);

}
