package com.krzysztof.studio.boardroom;

import com.krzysztof.studio.model.db.DbBoardroom;
import com.krzysztof.studio.model.rest.Boardroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
public class BoardroomController {

    @Autowired
    private BoardroomService boardroomService;

    @PostMapping(value = "/boardrooms")
    public ResponseEntity<?> create(@RequestBody @Valid DbBoardroom boardroom) {
        return new ResponseEntity<>(boardroomService.create(boardroom), HttpStatus.CREATED);
    }

    // TODO: replace with stream
    @GetMapping(value="/boardrooms")
    public List<Boardroom> read() {
        var dbBoardrooms = boardroomService.read();
        var boardrooms = new ArrayList<Boardroom>();
        for (DbBoardroom dbBoardroom : dbBoardrooms) {
            boardrooms.add(convertToView(dbBoardroom));
        }
        return boardrooms;
    }

    @GetMapping(value="/boardrooms/{name}")
    public Boardroom read(@PathVariable String name) {
        return convertToView(boardroomService.read(name));
    }

    @PutMapping(value = "/boardrooms/{name}")
    public void update(@PathVariable String name, @RequestBody @Valid Boardroom boardroom) {
        boardroomService.update(name, convertToDb(boardroom));
    }

    @DeleteMapping(value = "/boardrooms/{name}")
    public void delete(@PathVariable String name) {
        boardroomService.delete(name);
    }

    private Boardroom convertToView(DbBoardroom dbBoardroom) {
        var boardroom = new Boardroom();
        boardroom.setName(dbBoardroom.getName());
        boardroom.setId(dbBoardroom.getId());
        boardroom.setOrganizationName(dbBoardroom.getOrganizationName());
        boardroom.setLevel(dbBoardroom.getLevel());
        boardroom.setAvailable(dbBoardroom.isAvailable());
        boardroom.setSeats(dbBoardroom.getSeats());
        boardroom.setStandingPlaces(dbBoardroom.getStandingPlaces());
        boardroom.setSunbeds(dbBoardroom.getSunbeds());
        boardroom.setHammocks(dbBoardroom.getHammocks());
        boardroom.setEquipment(dbBoardroom.getEquipment());
        return boardroom;
    }

    private DbBoardroom convertToDb(Boardroom boardroom) {
        var dbBoardroom = new DbBoardroom();
        dbBoardroom.setName(boardroom.getName());
        dbBoardroom.setId(boardroom.getId());
        dbBoardroom.setOrganizationName(boardroom.getOrganizationName());
        dbBoardroom.setLevel(boardroom.getLevel());
        dbBoardroom.setAvailable(boardroom.isAvailable());
        dbBoardroom.setSeats(boardroom.getSeats());
        dbBoardroom.setStandingPlaces(boardroom.getStandingPlaces());
        dbBoardroom.setSunbeds(boardroom.getSunbeds());
        dbBoardroom.setHammocks(boardroom.getHammocks());
        dbBoardroom.setEquipment(boardroom.getEquipment());
        return dbBoardroom;
    }
}
