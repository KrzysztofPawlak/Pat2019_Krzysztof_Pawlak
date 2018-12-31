package com.krzysztof.studio.boardroom;

import com.krzysztof.studio.model.Boardroom;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardroomService {

    private List<Boardroom> boardrooms = new ArrayList<>();

    public ResponseEntity<?> create(Boardroom boardroom) {

        if (!exists(boardroom)) {
            boardrooms.add(boardroom);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Boardroom already exists.", HttpStatus.BAD_REQUEST);
    }

    public List<Boardroom> read() {
        return boardrooms;
    }

    public Boardroom read(String name) {
        return boardrooms.stream()
                .filter(boardroom -> name.equals(boardroom.getName()))
                .findFirst().orElse(null);
    }

    public void delete(String name) {
        boardrooms.removeIf(t -> t.getName().equals(name));
    }

    public void update(String name, Boardroom boardroomUpdated) {
        boardrooms.stream()
                .filter(boardroom -> name.equals(boardroom.getName()))
                .forEach(boardroom -> boardrooms.set(boardrooms.indexOf(boardroom), boardroomUpdated));
    }

    public boolean exists(Boardroom boardroom) {
        return read(boardroom.getName()) != null;
    }
}
