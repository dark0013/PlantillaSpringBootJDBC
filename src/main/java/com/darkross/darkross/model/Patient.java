package com.darkross.darkross.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    private Integer idpatient;
    private String firstname;
    private String lastname;
    private String dni;
    private String address;
    private String phone;
    private String email;


}
