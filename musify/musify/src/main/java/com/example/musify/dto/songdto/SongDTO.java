package com.example.musify.dto.songdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongDTO {
    private Integer id;
    @NotBlank(message = "Required field!")
    private String title;
    private Time duration;
    private Date creationDate;
}
