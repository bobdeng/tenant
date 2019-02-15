package cn.bobdeng.tenant.domain;

public interface TenantService {
    Tenant newTenant(Tenant tenant);

    void deleteTenant(Tenant tenant);

    RentContract newContact(RentContract rentContract);

    void stopContact(RentContract rentContract);

    void renewContact(RentContract rentContract, long end);
}
