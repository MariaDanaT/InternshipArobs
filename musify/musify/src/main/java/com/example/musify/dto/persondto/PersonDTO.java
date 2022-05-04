package com.example.musify.dto.persondto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String stageName;
    private Date birthday;
    private String activityStartDate;
    private String activityEndDate;

}
