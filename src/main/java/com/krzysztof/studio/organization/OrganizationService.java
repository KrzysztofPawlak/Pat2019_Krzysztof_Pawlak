package com.krzysztof.studio.organization;

import com.krzysztof.studio.model.Organization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationService {

    private List<Organization> organizations = new ArrayList();

    public ResponseEntity<?> create(Organization organization) {

        if (!exists(organization)) {
            organizations.add(organization);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>("organization already exists", HttpStatus.BAD_REQUEST);
    }

    public List<Organization> read() {
        return organizations;
    }

    public Organization read(String name) {
        return organizations.stream()
                .filter(organization -> name.equals(organization.getName()))
                .findFirst().orElse(null);
    }

    public void delete(String name) {
        organizations.removeIf(t -> t.getName().equals(name));
    }

    public void update(String name, Organization organizationUpdated) {
        organizations.stream()
                .filter(organization -> name.equals(organization.getName()))
                .forEach(organization -> organizations.set(organizations.indexOf(organization), organizationUpdated));
    }

    public boolean exists(Organization organization) {
        return read(organization.getName()) != null;
    }
}
