package com.fivet.buddy.util;

import java.util.Map;

public interface EmailUtil {
    Map<String, Object> sendEmail(String toAddress, String subject, String body);

    Map<String, Object> sendInviteEmail(String toAddress, String subject, String body);
}
