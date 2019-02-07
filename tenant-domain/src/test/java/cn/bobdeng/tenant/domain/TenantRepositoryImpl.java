package cn.bobdeng.tenant.domain;

import java.util.*;

public class TenantRepositoryImpl implements TenantRepository {
    private Map<Integer,RentContact> contactMap=new HashMap<>();
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
    public Optional<RentContact> findLastContact(int apartmentId) {
        return contactMap.values().stream()
                .filter(rentContact -> rentContact.getApartmentId()==apartmentId)
                .sorted(Comparator.comparingLong(RentContact::getStart).reversed())
                .findFirst();
    }

    @Override
    public RentContact saveContact(RentContact rentContact) {
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
    public Optional<RentContact> findContactById(int contactId) {
        return Optional.empty();
    }

    @Override
    public Optional<Tenant> findByContactAndMobile(int contactId, String mobile) {
        return tenantMap.values().stream()
                .filter(tenant -> tenant.getRentContact().getId()==contactId)
                .filter(tenant -> tenant.getMobile().equals(mobile))
                .findFirst();
    }
}
