package cn.bobdeng.tenant.domain;

import java.util.Optional;

public interface TenantRepository {
    Tenant newTenant(Tenant tenant);

    Optional<Tenant> findById(long id);

    void deleteTenant(Tenant tenant);
}
