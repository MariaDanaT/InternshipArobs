package com.example.musify.dto.playlistdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistWithSongsTitleDTO {
    private Integer idPlaylist;
    private List<String> songsTitle;
}
