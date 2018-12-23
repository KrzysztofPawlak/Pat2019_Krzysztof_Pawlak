package com.krzysztof.studio.organization;

import com.krzysztof.studio.model.Organization;
import org.junit.Assert;
import org.junit.Test;

public class OrganizationServiceTest {

    private OrganizationService service = new OrganizationService();

    @Test
    public void checkInitialSize() {
        Assert.assertTrue(service.read().size() == 0);
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
        Assert.assertTrue(service.read().size() == 3);
    }

    @Test
    public void checkNameOfAddedEntry() {
        var name = "foo";
        service.create(buildSampleEntry(name));
        Assert.assertTrue(service.read(name).getName().equals(name));
    }

    @Test
    public void checkSizeAfterCreateAndDeleteEntry() {
        var sampleOrganization = buildSampleEntry("foo");
        service.create(sampleOrganization);
        service.delete(sampleOrganization.getName());
        Assert.assertTrue(service.read().size() == 0);
    }

    @Test
    public void checkUpdatedEntryName() {
        var name = "foo";
        var sampleOrganization = buildSampleEntry(name);
        var updatedName = "bar";
        var sampleUpdatedOrganization = buildSampleEntry(updatedName);
        service.create(sampleOrganization);
        service.update(name, sampleUpdatedOrganization);
        Assert.assertTrue(service.read(updatedName).getName().equals("bar"));
    }
}
