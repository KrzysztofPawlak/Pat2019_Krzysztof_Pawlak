package com.krzysztof.studio.boardroom;

import com.krzysztof.studio.config.error.model.ResourceAlreadyExistsException;
import com.krzysztof.studio.config.error.model.ResourceNotFoundException;
import com.krzysztof.studio.model.db.DbBoardroom;
import com.krzysztof.studio.model.db.DbEquipment;
import com.krzysztof.studio.model.db.DbOrganization;
import com.krzysztof.studio.model.db.DbPhone;
import com.krzysztof.studio.model.rest.Boardroom;
import com.krzysztof.studio.model.rest.Equipment;
import com.krzysztof.studio.model.rest.Phone;
import com.krzysztof.studio.organization.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class BoardroomService {

    private final BoardroomRepository boardroomRepository;
    private final OrganizationRepository organizationRepository;

    BoardroomService(BoardroomRepository boardroomRepository, OrganizationRepository organizationRepository) {
        this.boardroomRepository = boardroomRepository;
        this.organizationRepository = organizationRepository;
    }

    Boardroom create(Boardroom boardroom) {
        var dbBoardroom = convertToDb(boardroom);
        if (exists(dbBoardroom)) {
            throw new ResourceAlreadyExistsException("Boardrooms already exists!");
        }
        checkOrganizationExists(dbBoardroom);
        return convertToView(boardroomRepository.save(dbBoardroom));
    }

    List<Boardroom> read() {
        var boardrooms = new ArrayList<Boardroom>();
        boardroomRepository.findAll().forEach(dbBoardroom -> boardrooms.add(convertToView(dbBoardroom)));
        return boardrooms;
    }

    Boardroom read(String name) {
        return convertToView(boardroomRepository.findById(name).orElseThrow(() -> new ResourceNotFoundException("No Boardrooms found!")));
    }

    void delete(String name) {
        if (boardroomRepository.existsById(name)) {
            boardroomRepository.deleteById(name);
        }
    }

    void update(String name, Boardroom boardroomUpdated) {
        var dbBoardroom = convertToDb(boardroomUpdated);
        if (!boardroomRepository.existsById(name)) {
            return;
        }
        checkOrganizationExists(dbBoardroom);
        boardroomRepository.save(dbBoardroom);
    }

    boolean exists(DbBoardroom dbBoardroom) {
        return boardroomRepository.existsById(dbBoardroom.getName());
    }

    private void checkOrganizationExists(DbBoardroom dbBoardroom) {
        if (!organizationRepository.existsById(dbBoardroom.getOrganization().getName())) {
            throw new ResourceNotFoundException("Specified organization is not exists!");
        }
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
        if (dbEquipment.isPhoneAvailable()) {
            equipment.setPhone(convertToView(dbEquipment.getPhone()));
        }
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
        if (equipment.isPhoneAvailable()) {
            dbEquipment.setPhone(convertToDb(equipment.getPhone()));
        }
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
