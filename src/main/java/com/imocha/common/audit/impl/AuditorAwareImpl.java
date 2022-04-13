package com.imocha.common.audit.impl;

import java.util.Optional;

import com.imocha.common.helper.UserHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Autowired
    private UserHelper userHelper;

    @Override
    public Optional<String> getCurrentAuditor() {
        String user = userHelper.getCurrentUserId();
        return Optional.of(Optional.ofNullable(user).orElse(null));
    }
}
