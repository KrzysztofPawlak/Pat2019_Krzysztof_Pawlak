package com.krzysztof.studio.organization;

import com.krzysztof.studio.config.error.model.ResourceAlreadyExistsException;
import com.krzysztof.studio.config.error.model.ResourceNotFoundException;
import com.krzysztof.studio.model.db.DbOrganization;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class OrganizationService {

    private final OrganizationRepository organizationRepository;

    OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    DbOrganization create(DbOrganization dbOrganization) {
        if (exists(dbOrganization)) {
            throw new ResourceAlreadyExistsException("Organizations already exists!");
        }
        return organizationRepository.save(dbOrganization);
    }

    List<DbOrganization> read() {
        var organizations = new ArrayList<DbOrganization>();
        organizationRepository.findAll().forEach(organizations::add);
        return organizations;
    }

    DbOrganization read(String name) {
        return organizationRepository.findById(name).orElseThrow(() -> new ResourceNotFoundException("No Organizations found!"));
    }

    void delete(String name) {
        if (organizationRepository.existsById(name)) {
            organizationRepository.deleteById(name);
        }
    }

    void update(String name, DbOrganization dbOrganizationUpdated) {
        if (organizationRepository.existsById(name)) {
            organizationRepository.save(dbOrganizationUpdated);
        }
    }

    private boolean exists(DbOrganization dbOrganization) {
        return organizationRepository.existsById(dbOrganization.getName());
    }
}
