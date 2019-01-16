package com.krzysztof.studio.organization;

import com.krzysztof.studio.config.error.model.ResourceAlreadyExistsException;
import com.krzysztof.studio.config.error.model.ResourceNotFoundException;
import com.krzysztof.studio.model.db.DbOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    public DbOrganization create(DbOrganization dbOrganization) {
        if (exists(dbOrganization)) throw new ResourceAlreadyExistsException("Organizations already exists!");
        return organizationRepository.save(dbOrganization);
    }

    public List<DbOrganization> read() {
        var organizations = new ArrayList<DbOrganization>();
        organizationRepository.findAll().forEach(organizations::add);
        return organizations;
    }

    public DbOrganization read(String name) {
        return organizationRepository.findById(name).orElseThrow(() -> new ResourceNotFoundException("No Organizations found!"));
    }

    public void delete(String name) {
        if (organizationRepository.existsById(name)) organizationRepository.deleteById(name);
    }

    public void update(String name, DbOrganization dbOrganizationUpdated) {
        if (organizationRepository.existsById(name)) organizationRepository.save(dbOrganizationUpdated);
    }

    public boolean exists(DbOrganization dbOrganization) {
        return organizationRepository.existsById(dbOrganization.getName());
    }
}
