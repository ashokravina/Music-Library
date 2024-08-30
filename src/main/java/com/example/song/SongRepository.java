// Write your code here
package com.example.song;

import java.util.*;

public interface SongRepository {
    ArrayList<Song> getSongs();

    Song getSongId(int SongId);

    Song addSong(Song song);

    Song updateSong(int songId, Song song);

    void deleteSong(int songId);
}