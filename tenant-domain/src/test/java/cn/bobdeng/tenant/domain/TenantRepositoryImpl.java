package cn.bobdeng.tenant.domain;

import java.util.*;

public class TenantRepositoryImpl implements TenantRepository {
    private Map<Integer,RentContact> contactMap=new HashMap<>();
    private int index=1;
    @Override
    public Tenant newTenant(Tenant tenant) {
        return null;
    }

    @Override
    public Optional<Tenant> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void deleteTenant(Tenant tenant) {

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
}
