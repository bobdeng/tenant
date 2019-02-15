package cn.bobdeng.tenant.server;

import cn.bobdeng.tenant.domain.RentContract;
import cn.bobdeng.tenant.domain.Tenant;
import cn.bobdeng.tenant.domain.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TenantRepositoryImpl implements TenantRepository {
    @Autowired
    TenantDAO tenantDAO;
    @Autowired
    RentContactDAO rentContactDAO;

    @Override
    public Tenant newTenant(Tenant tenant) {
        return tenantDAO.save(TenantDO.fromEntity(tenant)).toEntity();
    }

    @Override
    public Optional<Tenant> findById(long id) {
        return tenantDAO.findById(id).map(TenantDO::toEntity);
    }

    @Override
    public void deleteTenant(Tenant tenant) {
        tenantDAO.deleteById(tenant.getId());
    }

    @Override
    public List<Tenant> findTenants(int contractId) {
        return tenantDAO.findByRentContractId(contractId).map(TenantDO::toEntity).collect(Collectors.toList());
    }

    @Override
    public Optional<RentContract> findLastContract(int apartmentId) {
        return rentContactDAO.findTop1ByApartmentIdOrderByStartDesc(apartmentId).map(RentContractDO::toEntity);
    }

    @Override
    public RentContract saveContact(RentContract rentContact) {
        return rentContactDAO.save(RentContractDO.fromEntity(rentContact)).toEntity();
    }

    @Override
    public Optional<RentContract> findContractById(int contactId) {
        return rentContactDAO.findById(contactId).map(RentContractDO::toEntity);
    }

    @Override
    public Optional<Tenant> findByContractAndMobile(int contactId, String mobile) {
        return tenantDAO.findByRentContractIdAndMobile(contactId,mobile).map(TenantDO::toEntity);
    }
}
