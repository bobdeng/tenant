package cn.bobdeng.tenant.domain;

import java.util.List;
import java.util.Optional;

public interface TenantRepository {
    Tenant newTenant(Tenant tenant);

    Optional<Tenant> findById(long id);

    Optional<Tenant> findByContractAndMobile(int contractId, String mobile);

    List<Tenant> findTenants(int contactId);

    Optional<RentContract> findContractById(int contractId);

    void deleteTenant(Tenant tenant);

    Optional<RentContract> findLastContract(int apartmentId);

    RentContract saveContact(RentContract rentContract);
}
