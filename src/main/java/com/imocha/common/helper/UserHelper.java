package com.imocha.common.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserHelper {

    public String getCurrentUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        log.debug("principal:{}", principal);

        if (principal instanceof Jwt) {
            Jwt jwt = (Jwt) principal;
            String idString = jwt.getClaimAsString("sub");
            log.debug("JWT UID:{}", idString);
            return idString;
        } else {
            return null;
        }
    }
}
