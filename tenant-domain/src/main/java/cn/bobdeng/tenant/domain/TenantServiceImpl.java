package cn.bobdeng.tenant.domain;

public class TenantServiceImpl implements TenantService {
    TenantRepository tenantRepository;

    public TenantServiceImpl(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public Tenant newTenant(Tenant tenant) {
        if (tenantRepository.findByContractAndMobile(tenant.getRentContact().getId(),tenant.getMobile()).isPresent()) {
            throw new DuplicateTenantException();
        }
        tenant.setId(0);
        return tenantRepository.newTenant(tenant);
    }

    @Override
    public void deleteTenant(Tenant tenant) {
        tenantRepository.findById(tenant.getId())
                .ifPresent(tenantRepository::deleteTenant);
    }

    @Override
    public RentContract newContract(RentContract rentContract) {
        if (tenantRepository.findLastContract(rentContract.getApartmentId())
                .filter(RentContract::isActive)
                .isPresent()) {
            throw new DuplicateContactException();
        }
        rentContract.setActive(true);
        rentContract.setId(0);
        return tenantRepository.saveContact(rentContract);
    }

    @Override
    public void stopContract(RentContract rentContract) {
        rentContract.setActive(false);
        tenantRepository.findTenants(rentContract.getId())
                .forEach(tenantRepository::deleteTenant);
        tenantRepository.saveContact(rentContract);
    }

    @Override
    public void renewContract(RentContract rentContract, long end) {
        rentContract.setActive(true);
        rentContract.setEnd(end);
        tenantRepository.saveContact(rentContract);
    }
}
