package cn.bobdeng.tenant.server;

import cn.bobdeng.tenant.domain.RentContact;
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
    public List<Tenant> findApartmentTenants(int apartmentId) {
        return tenantDAO.findByApartmentId(apartmentId).map(TenantDO::toEntity).collect(Collectors.toList());
    }

    @Override
    public Optional<RentContact> findLastContact(int apartmentId) {
        return rentContactDAO.findTop1ByApartmentIdOrderByStartDesc(apartmentId).map(RentContactDO::toEntity);
    }

    @Override
    public RentContact saveContact(RentContact rentContact) {
        return rentContactDAO.save(RentContactDO.fromEntity(rentContact)).toEntity();
    }
}
