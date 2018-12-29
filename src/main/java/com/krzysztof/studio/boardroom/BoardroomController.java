package com.krzysztof.studio.boardroom;

import com.krzysztof.studio.model.Boardroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardroomController {

    @Autowired
    private BoardroomService boardroomService;

    @RequestMapping(method = RequestMethod.POST, value = "/boardrooms")
    public ResponseEntity<?> create(Boardroom boardroom) {
       return boardroomService.create(boardroom);
    }

    @RequestMapping(method = RequestMethod.GET, value="/boardrooms")
    public List<Boardroom> read() {
        return boardroomService.read();
    }

    @RequestMapping(method = RequestMethod.GET, value="/boardrooms/{name}")
    public Boardroom read(String name) {
        return boardroomService.read(name);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/boardrooms/{name}")
    public void update(String name, Boardroom boardroom) {
        boardroomService.update(name, boardroom);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/boardrooms/{name}")
    public void delete(String name) {
        boardroomService.delete(name);
    }
}
