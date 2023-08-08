package jw.project.baemin.delivery.presentation.request;

import jw.project.baemin.delivery.domain.Rider;

public record CreateRiderRequest(String email, String password) {

    public Rider toEntity() {
        return Rider.builder()
            .email(email)
            .password(password)
            .workingNow(false)
            .build();
    }
}
