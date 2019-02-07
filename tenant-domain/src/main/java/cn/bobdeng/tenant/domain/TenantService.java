package cn.bobdeng.tenant.domain;

public interface TenantService {
    Tenant newTenant(Tenant tenant);

    void deleteTenant(Tenant tenant);

    RentContact newContact(RentContact rentContact);

    void stopContact(RentContact rentContact);

    void renewContact(RentContact rentContact,long end);
}
