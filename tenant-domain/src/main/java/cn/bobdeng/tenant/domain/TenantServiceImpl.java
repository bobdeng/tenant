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

    @Override
    public RentContact newContact(RentContact rentContact) {
        if (tenantRepository.findLastContact(rentContact.getApartmentId())
                .filter(RentContact::isActive)
                .isPresent()) {
            throw new DuplicateContactException();
        }
        rentContact.setActive(true);
        rentContact.setId(0);
        return tenantRepository.saveContact(rentContact);
    }

    @Override
    public void stopContact(RentContact rentContact) {
        rentContact.setActive(false);
        tenantRepository.findApartmentTenants(rentContact.getApartmentId())
                .forEach(tenantRepository::deleteTenant);
        tenantRepository.saveContact(rentContact);
    }

    @Override
    public void renewContact(RentContact rentContact, long end) {
        rentContact.setActive(true);
        rentContact.setEnd(end);
        tenantRepository.saveContact(rentContact);
    }
}
