package cn.bobdeng.tenant.domain;

import cn.bobdeng.domainevent.EventPublisher;
import cn.bobdeng.tenant.domain.events.NewTenantEvent;
import cn.bobdeng.tenant.domain.events.RemoveTenantEvent;
import cn.bobdeng.tenant.domain.events.TenantEvents;

public class TenantServiceImpl implements TenantService {
    private final TenantRepository tenantRepository;
    private final EventPublisher eventPublisher;
    public TenantServiceImpl(TenantRepository tenantRepository, EventPublisher eventPublisher) {
        this.tenantRepository = tenantRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Tenant newTenant(Tenant tenant) {
        if (isSameExist(tenant)) {
            throw new DuplicateTenantException();
        }
        tenant.setId(0);
        tenant = tenantRepository.newTenant(tenant);
        eventPublisher.publish(TenantEvents.NEW_TENANT_EVENT,new NewTenantEvent(tenant));
        return tenant;
    }

    private boolean isSameExist(Tenant tenant) {
        if (tenant.getMobile() == null || tenant.getMobile().equals("")) {
            return false;
        }
        return tenantRepository.findTenants(tenant.getRentContact().getId())
                .stream()
                .anyMatch(old -> old.isSame(tenant));
    }

    @Override
    public void deleteTenant(Tenant tenant) {
        tenantRepository.findById(tenant.getId())
                .ifPresent(this::removeTenant);
    }

    private void removeTenant(Tenant tenant) {
        tenantRepository.deleteTenant(tenant);
        eventPublisher.publish(TenantEvents.REMOVE_TENANT_EVENT,new RemoveTenantEvent(tenant));
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
