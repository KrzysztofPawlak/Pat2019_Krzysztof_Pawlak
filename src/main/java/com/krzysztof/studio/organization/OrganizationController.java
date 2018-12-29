package com.krzysztof.studio.organization;

import com.krzysztof.studio.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(method = RequestMethod.POST, value = "/organizations")
    public ResponseEntity<?> create(@RequestBody Organization organization) {
        return organizationService.create(organization);
    }

    @RequestMapping(method = RequestMethod.GET, value="/organizations")
    public List<Organization> read() {
        return organizationService.read();
    }

    @RequestMapping(method = RequestMethod.GET, value="/organizations/{name}")
    public Organization read(String name) {
        return organizationService.read(name);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/organizations/{name}")
    public void update(String name, Organization organization) {
        organizationService.update(name, organization);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/organizations/{name}")
    public void delete(String name) {
        organizationService.delete(name);
    }
}
