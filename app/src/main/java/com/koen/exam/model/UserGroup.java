package com.koen.exam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGroup {
    private String id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
}
