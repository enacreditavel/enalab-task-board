package com.enalab.board.auth.infrastructure.api.dto;

import com.enalab.board.auth.application.profile.input.CreateProfileInput;

public record CreateProfileRequest(String name) {
    public CreateProfileInput toInput(){
        return new CreateProfileInput(name);
    }
}
