package com.krzysztof.studio.boardroom;

import com.krzysztof.studio.model.Boardroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
public class BoardroomController {

    @Autowired
    private BoardroomService boardroomService;

    @PostMapping(value = "/boardrooms")
    public ResponseEntity<?> create(@RequestBody @Valid Boardroom boardroom) {
        return new ResponseEntity<>(boardroomService.create(boardroom), HttpStatus.CREATED);
    }

    @GetMapping(value="/boardrooms")
    public List<Boardroom> read() {
        return boardroomService.read();
    }

    @GetMapping(value="/boardrooms/{name}")
    public Boardroom read(@PathVariable String name) {
        return boardroomService.read(name);
    }

    @PutMapping(value = "/boardrooms/{name}")
    public void update(@PathVariable String name, @RequestBody @Valid Boardroom boardroom) {
        boardroomService.update(name, boardroom);
    }

    @DeleteMapping(value = "/boardrooms/{name}")
    public void delete(@PathVariable String name) {
        boardroomService.delete(name);
    }
}
