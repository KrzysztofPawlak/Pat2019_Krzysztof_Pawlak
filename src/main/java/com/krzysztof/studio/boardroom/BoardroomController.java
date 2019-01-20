package com.krzysztof.studio.boardroom;

import com.krzysztof.studio.model.db.DbBoardroom;
import com.krzysztof.studio.model.db.DbEquipment;
import com.krzysztof.studio.model.db.DbOrganization;
import com.krzysztof.studio.model.db.DbPhone;
import com.krzysztof.studio.model.rest.Boardroom;
import com.krzysztof.studio.model.rest.Equipment;
import com.krzysztof.studio.model.rest.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static com.krzysztof.studio.config.ApiConfig.BOARDROOMS;

@Validated
@RestController
@RequestMapping(value = BOARDROOMS)
public class BoardroomController {

    @Autowired
    private BoardroomService boardroomService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid Boardroom boardroom) {
        return new ResponseEntity<>(boardroomService.create(convertToDb(boardroom)), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Boardroom> read() {
        var boardrooms = new ArrayList<Boardroom>();
        boardroomService.read().stream().forEach(dbBoardroom -> boardrooms.add(convertToView(dbBoardroom)));
        return boardrooms;
    }

    @GetMapping(value = "{name}")
    public Boardroom read(@PathVariable String name) {
        return convertToView(boardroomService.read(name));
    }

    @PutMapping(value = "{name}")
    public void update(@PathVariable String name, @RequestBody @Valid Boardroom boardroom) {
        boardroomService.update(name, convertToDb(boardroom));
    }

    @DeleteMapping(value = "{name}")
    public void delete(@PathVariable String name) {
        boardroomService.delete(name);
    }

    private Boardroom convertToView(DbBoardroom dbBoardroom) {
        var boardroom = new Boardroom();
        boardroom.setName(dbBoardroom.getName());
        boardroom.setId(dbBoardroom.getId());
        boardroom.setOrganizationName(dbBoardroom.getOrganization().getName());
        boardroom.setLevel(dbBoardroom.getLevel());
        boardroom.setAvailable(dbBoardroom.isAvailable());
        boardroom.setSeats(dbBoardroom.getSeats());
        boardroom.setStandingPlaces(dbBoardroom.getStandingPlaces());
        boardroom.setSunbeds(dbBoardroom.getSunbeds());
        boardroom.setHammocks(dbBoardroom.getHammocks());
        boardroom.setEquipment(convertToView(dbBoardroom.getEquipment()));
        return boardroom;
    }

    private Equipment convertToView(DbEquipment dbEquipment) {
        var equipment = new Equipment();
        equipment.setId(dbEquipment.getId());
        equipment.setProjectorName(dbEquipment.getProjectorName());
        equipment.setPhoneAvailable(dbEquipment.isPhoneAvailable());
        if (dbEquipment.isPhoneAvailable()) equipment.setPhone(convertToView(dbEquipment.getPhone()));
        return equipment;
    }

    private Phone convertToView(DbPhone dbPhone) {
        var phone = new Phone();
        phone.setId(dbPhone.getId());
        phone.setInternalNumber(dbPhone.getInternalNumber());
        phone.setExternalNumber(dbPhone.getExternalNumber());
        phone.setPhoneInterface(dbPhone.getPhoneInterface());
        return phone;
    }

    private DbBoardroom convertToDb(Boardroom boardroom) {
        var dbBoardroom = new DbBoardroom();
        dbBoardroom.setName(boardroom.getName());
        dbBoardroom.setId(boardroom.getId());
        dbBoardroom.setOrganization(new DbOrganization(boardroom.getOrganizationName()));
        dbBoardroom.setLevel(boardroom.getLevel());
        dbBoardroom.setAvailable(boardroom.isAvailable());
        dbBoardroom.setSeats(boardroom.getSeats());
        dbBoardroom.setStandingPlaces(boardroom.getStandingPlaces());
        dbBoardroom.setSunbeds(boardroom.getSunbeds());
        dbBoardroom.setHammocks(boardroom.getHammocks());
        dbBoardroom.setEquipment(convertToDb(boardroom.getEquipment()));
        return dbBoardroom;
    }

    private DbEquipment convertToDb(Equipment equipment) {
        var dbEquipment = new DbEquipment();
        dbEquipment.setId(equipment.getId());
        dbEquipment.setProjectorName(equipment.getProjectorName());
        dbEquipment.setPhoneAvailable(equipment.isPhoneAvailable());
        if (equipment.isPhoneAvailable()) dbEquipment.setPhone(convertToDb(equipment.getPhone()));
        return dbEquipment;
    }

    private DbPhone convertToDb(Phone phone) {
        var dbPhone = new DbPhone();
        dbPhone.setId(phone.getId());
        dbPhone.setInternalNumber(phone.getInternalNumber());
        dbPhone.setExternalNumber(phone.getExternalNumber());
        dbPhone.setPhoneInterface(phone.getPhoneInterface());
        return dbPhone;
    }
}
