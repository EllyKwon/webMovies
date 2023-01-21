package com.webMovies.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginRequestVO {

    @JsonProperty("userId")
    private String userId;
    @JsonProperty("pwd")
    private String pwd;

}
