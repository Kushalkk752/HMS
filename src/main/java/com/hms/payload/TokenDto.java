package com.hms.payload;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class TokenDto {
    private String token;
    private String type;
}
