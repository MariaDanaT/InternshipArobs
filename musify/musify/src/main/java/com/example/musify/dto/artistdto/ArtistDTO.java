package com.example.musify.dto.artistdto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@Builder
public class ArtistDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String stageName;
    private Date birthday;
    private String activityStartDate;
    private String activityEndDate;
    private String type;

    public ArtistDTO(Integer id, String firstName, String lastName, String stageName, Date birthday, String activityStartDate, String activityEndDate, String type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.stageName = stageName;
        this.birthday = birthday;
        this.activityStartDate = activityStartDate;
        this.activityEndDate = activityEndDate;
        this.type = type;
    }

}
