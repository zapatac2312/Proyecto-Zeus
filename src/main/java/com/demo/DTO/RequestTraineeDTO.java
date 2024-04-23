package com.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestTraineeDTO {
    private String name ;
    private String email;
    private String password;
    private String oldPassword;
}
