package com.krzysztof.studio.boardroom;

import com.krzysztof.studio.config.error.model.ResourceAlreadyExistsException;
import com.krzysztof.studio.config.error.model.ResourceNotFoundException;
import com.krzysztof.studio.model.db.DbBoardroom;
import com.krzysztof.studio.organization.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardroomService {

    @Autowired
    BoardroomRepository boardroomRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    public DbBoardroom create(DbBoardroom dbBoardroom) {
        if(exists(dbBoardroom)) throw new ResourceAlreadyExistsException("Boardrooms already exists!");
        checkOrganizationExists(dbBoardroom);
        return boardroomRepository.save(dbBoardroom);
    }

    public List<DbBoardroom> read() {
        var boardrooms = new ArrayList<DbBoardroom>();
        boardroomRepository.findAll().forEach(boardrooms::add);
        return boardrooms;
    }

    public DbBoardroom read(String name) {
        return boardroomRepository.findById(name).orElseThrow(() -> new ResourceNotFoundException("No Boardrooms found!"));
    }

    public void delete(String name) {
        if (boardroomRepository.existsById(name)) boardroomRepository.deleteById(name);
    }

    public void update(String name, DbBoardroom dbBoardroomUpdated) {
        if (boardroomRepository.existsById(name)) boardroomRepository.save(dbBoardroomUpdated);
    }

    public boolean exists(DbBoardroom dbBoardroom) {
        return boardroomRepository.existsById(dbBoardroom.getName());
    }

    private void checkOrganizationExists(DbBoardroom dbBoardroom) {
        if (!organizationRepository.existsById(dbBoardroom.getOrganization().getName()))
            throw new ResourceNotFoundException("Specified organization is not exists!");
    }
}
