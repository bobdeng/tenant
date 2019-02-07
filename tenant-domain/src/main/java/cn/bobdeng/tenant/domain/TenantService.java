package cn.bobdeng.tenant.domain;

public interface TenantService {
    Tenant newTenant(Tenant tenant);

    void deleteTenant(Tenant tenant);
}
