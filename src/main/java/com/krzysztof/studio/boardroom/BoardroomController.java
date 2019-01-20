package com.krzysztof.studio.boardroom;

import com.krzysztof.studio.model.rest.Boardroom;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.krzysztof.studio.config.ApiConfig.BOARDROOMS;

@Validated
@RestController
@RequestMapping(value = BOARDROOMS)
class BoardroomController {

    private final BoardroomService boardroomService;

    BoardroomController(BoardroomService boardroomService) {
        this.boardroomService = boardroomService;
    }

    @PostMapping
    ResponseEntity<?> create(@RequestBody @Valid Boardroom boardroom) {
        return new ResponseEntity<>(boardroomService.create(boardroom), HttpStatus.CREATED);
    }

    @GetMapping
    List<Boardroom> read() {
        return boardroomService.read();
    }

    @GetMapping(value = "{name}")
    Boardroom read(@PathVariable String name) {
        return boardroomService.read(name);
    }

    @PutMapping(value = "{name}")
    void update(@PathVariable String name, @RequestBody @Valid Boardroom boardroom) {
        boardroomService.update(name, boardroom);
    }

    @DeleteMapping(value = "{name}")
    void delete(@PathVariable String name) {
        boardroomService.delete(name);
    }

}
