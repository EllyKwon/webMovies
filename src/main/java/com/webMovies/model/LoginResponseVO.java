package com.webMovies.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginResponseVO {

    @JsonProperty("status")
    private String status;
    @JsonProperty("message")
    private String message;

}
