package com.example.musify.dto.albumdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDTO {
    private Integer id;
    @NotBlank(message = "Required field!")
    private String title;
    private String description;
    @NotBlank(message = "Required field!")
    private String genre;
    private Date releaseDate;
    @NotBlank(message = "Required field!")
    private String label;
}
