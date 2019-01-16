package com.krzysztof.studio.organization;

import com.krzysztof.studio.model.db.DbOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<DbOrganization, String> { }
