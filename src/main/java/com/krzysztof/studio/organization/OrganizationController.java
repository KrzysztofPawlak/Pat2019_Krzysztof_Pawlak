package com.krzysztof.studio.organization;

import com.krzysztof.studio.model.db.DbOrganization;
import com.krzysztof.studio.model.rest.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(method = RequestMethod.POST, value = "/organizations")
    public ResponseEntity<?> create(@RequestBody @Valid Organization organization) {
        return new ResponseEntity<>(organizationService.create(convertToDb(organization)), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET, value="/organizations")
    public List<Organization> read() {
        var dbOrganizations = organizationService.read();
        var organizations = new ArrayList<Organization>();
        for (DbOrganization dbOrganization : dbOrganizations) {
            organizations.add(convertToView(dbOrganization));
        }
        return organizations;
    }

    @RequestMapping(method = RequestMethod.GET, value="/organizations/{name}")
    public Organization read(@PathVariable String name) {
        return convertToView(organizationService.read(name));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/organizations/{name}")
    public void update(@PathVariable String name, @RequestBody @Valid Organization organization) {
        organizationService.update(name, convertToDb(organization));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/organizations/{name}")
    public void delete(@PathVariable String name) {
        organizationService.delete(name);
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
