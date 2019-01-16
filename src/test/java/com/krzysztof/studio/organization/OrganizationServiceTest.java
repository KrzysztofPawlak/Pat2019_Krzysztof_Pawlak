package com.krzysztof.studio.organization;

import com.krzysztof.studio.model.rest.Organization;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrganizationServiceTest {

    private OrganizationService service = new OrganizationService();

    @Test
    public void checkInitialSize() {
        assertThat(service.read().size() == 0).isTrue();
    }

    private Organization buildSampleEntry(String name) {
        var organization = new Organization();
        organization.setName(name);
        return organization;
    }

    @Test
    public void checkSizeAfterAddedEntries() {
        service.create(buildSampleEntry("foo"));
        service.create(buildSampleEntry("bar"));
        service.create(buildSampleEntry("baz"));
        assertThat(service.read().size() == 3).isTrue();
    }

    @Test
    public void checkNameOfAddedEntry() {
        var name = "foo";
        service.create(buildSampleEntry(name));
        assertThat(service.read(name).getName().equals(name)).isTrue();
    }

    @Test
    public void checkSizeAfterCreateAndDeleteEntry() {
        var sampleOrganization = buildSampleEntry("foo");
        service.create(sampleOrganization);
        service.delete(sampleOrganization.getName());
        assertThat(service.read().size() == 0).isTrue();
    }

    @Test
    public void checkUpdatedEntryName() {
        var name = "foo";
        var sampleOrganization = buildSampleEntry(name);
        var updatedName = "bar";
        var sampleUpdatedOrganization = buildSampleEntry(updatedName);
        service.create(sampleOrganization);
        service.update(name, sampleUpdatedOrganization);
        assertThat(service.read(updatedName).getName().equals("bar")).isTrue();
    }
}
