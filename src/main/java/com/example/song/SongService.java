package com.example.song;

import java.util.*;

import com.example.song.Song;
import com.example.song.SongRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

public class SongService implements SongRepository {
    private static HashMap<Integer, Song> playlist = new HashMap<>();
    int uniqueSongId = 6;

    public SongService() {
        playlist.put(1, new Song(1, "Butta Bomma", "Ramajogayya Sastry", "Armaan Malik", "Thaman S"));
        playlist.put(2, new Song(2, "Kathari Poovazhagi", "Vijay", "Benny Dayal, Swetha Mohan", "A.R. Rahman"));
        playlist.put(3, new Song(3, "Tum Hi Ho", "Mithoon", "Arijit Singh", "Mithoon"));
        playlist.put(4, new Song(4, "Vizhiyil", "Vairamuthu", "Unni Menon", "A.R. Rahman"));
        playlist.put(5, new Song(5, "Nenjame", "Panchu Arunachalam", "S.P.Balasubrahmanyam", "Ilaiyaraaja"));
    }

    @Override
    public ArrayList<Song> getSongs() {
        Collection<Song> songsCollection = playlist.values();
        ArrayList<Song> song = new ArrayList<>(songsCollection);
        return song;
    }

    @Override
    public Song getSongId(int songId) {
        Song song = playlist.get(songId);
        if (song == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return song;
    }

    @Override
    public Song addSong(Song song) {
        song.setId(uniqueSongId);
        playlist.put(uniqueSongId, song);
        uniqueSongId += 1;
        return song;

    }

    @Override
    public Song updateSong(int songId, Song song) {
        Song exitingSong = playlist.get(songId);
        if (exitingSong == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (song.getSongName() != null) {
            exitingSong.setSongName(song.getSongName());
        }
        if (song.getLyricist() != null) {
            exitingSong.setLyricist(song.getLyricist());
        }
        if (song.getSinger() != null) {
            exitingSong.setSinger(song.getSinger());
        }
        if (song.getMusicDirector() != null) {
            exitingSong.setMusicDirector(song.getMusicDirector());
        }
        return exitingSong;
    }

    @Override
    public void deleteSong(int songId) {
        Song exitingSong = playlist.get(songId);
        if (exitingSong == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            playlist.remove(songId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

}