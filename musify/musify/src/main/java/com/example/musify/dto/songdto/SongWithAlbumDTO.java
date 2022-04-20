package com.example.musify.dto.songdto;

import com.example.musify.entity.Album;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SongWithAlbumDTO {
    private Integer id;
    @NotBlank(message = "Required field!")
    private String title;
    private Time duration;
    private Date creationDate;
    private Integer album;
}
