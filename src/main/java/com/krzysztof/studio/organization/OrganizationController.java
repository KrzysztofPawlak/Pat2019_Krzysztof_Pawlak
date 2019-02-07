package com.krzysztof.studio.organization;

import com.krzysztof.studio.model.rest.Organization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.krzysztof.studio.config.Constants.ORGANIZATIONS;

@RestController
@RequestMapping(value = ORGANIZATIONS)
class OrganizationController {

    private final OrganizationService organizationService;

    OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    ResponseEntity<Organization> create(@RequestBody @Valid Organization organization) {
        return new ResponseEntity<>(organizationService.create(organization), HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    List<Organization> read() {
        return organizationService.read();
    }

    @GetMapping(value="{name}")
    Organization read(@PathVariable String name) {
        return organizationService.read(name);
    }

    @PutMapping(value = "{name}")
    void update(@PathVariable String name, @RequestBody @Valid Organization organization) {
        organizationService.update(name, organization);
    }

    @DeleteMapping(value = "{name}")
    void delete(@PathVariable String name) {
        organizationService.delete(name);
    }

}
