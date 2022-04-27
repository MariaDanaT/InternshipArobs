package com.example.musify.dto.playlistdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDTO {
    private Integer id;
    private String name;
    private String type;
    private Date createdDate;
    private Date lastUpdateDate;

}
