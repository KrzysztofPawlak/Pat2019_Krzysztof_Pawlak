package com.krzysztof.studio.boardroom;

import com.krzysztof.studio.config.error.model.ResourceAlreadyExistsException;
import com.krzysztof.studio.config.error.model.ResourceNotFoundException;
import com.krzysztof.studio.model.Boardroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardroomService {

    @Autowired
    BoardroomRepository boardroomRepository;

    public Boardroom create(Boardroom boardroom) {
        if(exists(boardroom)) throw new ResourceAlreadyExistsException("Boardrooms already exists!");
        return boardroomRepository.save(boardroom);
    }

    public List<Boardroom> read() {
        var boardrooms = new ArrayList<Boardroom>();
        boardroomRepository.findAll().forEach(boardrooms::add);
        return boardrooms;
    }

    public Boardroom read(String name) {
        return boardroomRepository.findById(name).orElseThrow(() -> new ResourceNotFoundException("No Boardrooms found!"));
    }

    public void delete(String name) {
        if (boardroomRepository.existsById(name)) boardroomRepository.deleteById(name);
    }

    public void update(String name, Boardroom boardroomUpdated) {
        if (boardroomRepository.existsById(name)) boardroomRepository.save(boardroomUpdated);
    }

    public boolean exists(Boardroom boardroom) {
        return boardroomRepository.existsById(boardroom.getName());
    }
}
