package cn.bobdeng.tenant.domain;

import java.util.*;

public class TenantRepositoryImpl implements TenantRepository {
    private Map<Integer, RentContract> contactMap=new HashMap<>();
    private Map<Long,Tenant> tenantMap=new HashMap<>();
    private int index=1;
    @Override
    public Tenant newTenant(Tenant tenant) {
        tenant.setId(index++);
        tenantMap.put(tenant.getId(),tenant);
        return tenant;
    }

    @Override
    public Optional<Tenant> findById(long id) {
        return Optional.of(tenantMap.get(id));
    }

    @Override
    public void deleteTenant(Tenant tenant) {
        tenantMap.remove(tenant.getId());
    }

    @Override
    public Optional<RentContract> findLastContract(int apartmentId) {
        return contactMap.values().stream()
                .filter(rentContact -> rentContact.getApartmentId()==apartmentId)
                .sorted(Comparator.comparingLong(RentContract::getStart).reversed())
                .findFirst();
    }

    @Override
    public RentContract saveContact(RentContract rentContact) {
        if(rentContact.getId()==0){
            rentContact.setId(index++);
        }
        contactMap.put(rentContact.getId(),rentContact);
        return rentContact;
    }

    @Override
    public List<Tenant> findTenants(int contactId) {
        return null;
    }

    @Override
    public Optional<RentContract> findContractById(int contactId) {
        return Optional.empty();
    }

    @Override
    public Optional<Tenant> findByContractAndMobile(int contactId, String mobile) {
        return tenantMap.values().stream()
                .filter(tenant -> tenant.getRentContact().getId()==contactId)
                .filter(tenant -> tenant.getMobile().equals(mobile))
                .findFirst();
    }
}
