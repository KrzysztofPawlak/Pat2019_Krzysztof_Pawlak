package com.krzysztof.studio.boardroom;

import com.krzysztof.studio.model.Boardroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardroomService {

    @Autowired
    BoardroomRepository boardroomRepository;

    public ResponseEntity<?> create(Boardroom boardroom) {

        if (!boardroomRepository.existsById(boardroom.getId())) {
            boardroomRepository.save(boardroom);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Boardroom already exists.", HttpStatus.BAD_REQUEST);
    }

    public List<Boardroom> read() {
        var boardrooms = new ArrayList<Boardroom>();
        boardroomRepository.findAll().forEach(boardrooms::add);
        return boardrooms;
    }

    public Boardroom read(String name) {
        return boardroomRepository.findById(name).orElseThrow();
    }

    public void delete(String name) {
        boardroomRepository.deleteById(name);
    }

    public void update(String name, Boardroom boardroomUpdated) {
        if (boardroomRepository.existsById(name)) boardroomRepository.save(boardroomUpdated);
    }

    public boolean exists(Boardroom boardroom) {
        return read(boardroom.getName()) != null;
    }
}
