package cn.bobdeng.tenant.domain;

public interface TenantService {
    Tenant newTenant(Tenant tenant);

    void deleteTenant(Tenant tenant);

    RentContract newContract(RentContract rentContract);

    void stopContract(RentContract rentContract);

    void renewContract(RentContract rentContract, long end);
}
