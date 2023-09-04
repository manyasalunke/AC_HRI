package net.javaguides.springboot.payloads;

import lombok.Data;

@Data
public class JWTAuthResponse {
    private String token;
    private EmployeeDTO employee;

}
