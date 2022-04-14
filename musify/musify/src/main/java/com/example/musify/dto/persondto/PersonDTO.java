package com.example.musify.dto.persondto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class PersonDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String stageName;
    private Date birthday;
    private String activityStartDate;
    private String activityEndDate;


    public PersonDTO(Integer id, String firstName, String lastName, String stageName, Date birthday, String activityStartDate, String activityEndDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.stageName = stageName;
        this.birthday = birthday;
        this.activityStartDate = activityStartDate;
        this.activityEndDate = activityEndDate;
    }

}
