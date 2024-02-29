package com.serby.springbootrestapi.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Student {

    private String firstName;

    private String lastName;

    private int id;


}
