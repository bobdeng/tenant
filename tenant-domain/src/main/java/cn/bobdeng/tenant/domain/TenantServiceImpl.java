package cn.bobdeng.tenant.domain;

public class TenantServiceImpl implements TenantService {
    TenantRepository tenantRepository;

    public TenantServiceImpl(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public Tenant newTenant(Tenant tenant) {
        tenant.setId(0);
        return tenantRepository.newTenant(tenant);
    }

    @Override
    public void deleteTenant(Tenant tenant) {
        tenantRepository.findById(tenant.getId())
                .ifPresent(tenantRepository::deleteTenant);
    }
}
