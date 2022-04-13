package com.imocha.common.helper;

import com.imocha.lms.users.entities.Users;
import com.imocha.lms.users.service.UsersService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserHelper {

    @Lazy
    @Autowired
    private UsersService usersService;

    public Users getCurrentLoginUser() {
        String currentUserId = this.getCurrentUserId();

        if (StringUtils.isBlank(currentUserId)) {
            return null;
        }

        return usersService.getByKeycloakId(currentUserId);
    }

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
