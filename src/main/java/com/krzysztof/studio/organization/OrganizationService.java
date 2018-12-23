package com.krzysztof.studio.organization;

import com.krzysztof.studio.model.Organization;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationService {

    private List<Organization> organizations = new ArrayList<>();

    public void create(Organization organization) {
        organizations.add(organization);
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
}
