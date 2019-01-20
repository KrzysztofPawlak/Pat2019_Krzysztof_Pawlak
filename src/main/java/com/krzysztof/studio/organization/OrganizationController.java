package com.krzysztof.studio.organization;

import com.krzysztof.studio.model.db.DbOrganization;
import com.krzysztof.studio.model.rest.Organization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static com.krzysztof.studio.config.ApiConfig.ORGANIZATIONS;

@RestController
@RequestMapping(value = ORGANIZATIONS)
class OrganizationController {

    private final OrganizationService organizationService;

    OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    ResponseEntity<?> create(@RequestBody @Valid Organization organization) {
        return new ResponseEntity<>(organizationService.create(convertToDb(organization)), HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    List<Organization> read() {
        var organizations = new ArrayList<Organization>();
        organizationService.read().stream().forEach(dbOrganization -> organizations.add(convertToView(dbOrganization)));
        return organizations;
    }

    @GetMapping(value="{name}")
    Organization read(@PathVariable String name) {
        return convertToView(organizationService.read(name));
    }

    @PutMapping(value = "{name}")
    void update(@PathVariable String name, @RequestBody @Valid Organization organization) {
        organizationService.update(name, convertToDb(organization));
    }

    @DeleteMapping(value = "{name}")
    void delete(@PathVariable String name) {
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
