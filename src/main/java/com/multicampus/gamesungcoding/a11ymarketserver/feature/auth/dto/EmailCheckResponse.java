package com.multicampus.gamesungcoding.a11ymarketserver.feature.auth.dto;

import com.multicampus.gamesungcoding.a11ymarketserver.feature.auth.status.EmailCheckStatus;

public record EmailCheckResponse(EmailCheckStatus isAvailable) {
}
