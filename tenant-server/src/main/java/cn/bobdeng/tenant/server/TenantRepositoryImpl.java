package cn.bobdeng.tenant.server;

import cn.bobdeng.tenant.domain.Tenant;
import cn.bobdeng.tenant.domain.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TenantRepositoryImpl implements TenantRepository {
    @Autowired
    TenantDAO tenantDAO;

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
}
