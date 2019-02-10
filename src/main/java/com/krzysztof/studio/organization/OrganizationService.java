package com.krzysztof.studio.organization;

import com.krzysztof.studio.config.error.model.ResourceAlreadyExistsException;
import com.krzysztof.studio.config.error.model.ResourceNotFoundException;
import com.krzysztof.studio.model.db.DbOrganization;
import com.krzysztof.studio.model.rest.Organization;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class OrganizationService {

    private final OrganizationRepository organizationRepository;

    OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    Organization create(Organization organization) {
        var dbOrganization = convertToDb(organization);
        if (exists(dbOrganization)) {
            throw new ResourceAlreadyExistsException("Organizations already exists!");
        }
        return convertToView(organizationRepository.save(dbOrganization));
    }

    List<Organization> read() {
        return organizationRepository.findAll().stream().map(dbOrganization -> convertToView(dbOrganization)).collect(Collectors.toList());
    }

    Organization read(String name) {
        return convertToView(organizationRepository.findById(name).orElseThrow(() -> new ResourceNotFoundException("No Organizations found!")));
    }

    void delete(String name) {
        if (organizationRepository.existsById(name)) {
            organizationRepository.deleteById(name);
        }
    }

    void update(String name, Organization organizationUpdated) {
        var dbOrganization = convertToDb(organizationUpdated);
        if (organizationRepository.existsById(name)) {
            organizationRepository.save(dbOrganization);
        }
    }

    private boolean exists(DbOrganization dbOrganization) {
        return organizationRepository.existsById(dbOrganization.getName());
    }

    private Organization convertToView(DbOrganization dbOrganization) {
        var organization = new Organization();
        organization.setName(dbOrganization.getName());
        return organization;
    }

    private DbOrganization convertToDb(Organization organization) {
        var dbOrganization = new DbOrganization();
        dbOrganization.setName(organization.getName());
        return dbOrganization;
    }
}
