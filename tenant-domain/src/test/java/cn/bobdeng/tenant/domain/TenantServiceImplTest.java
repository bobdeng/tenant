package cn.bobdeng.tenant.domain;

import cn.bobdeng.domainevent.EventPublisher;
import cn.bobdeng.tenant.domain.events.NewTenantEvent;
import cn.bobdeng.tenant.domain.events.RemoveTenantEvent;
import cn.bobdeng.tenant.domain.events.RenewContractEvent;
import cn.bobdeng.tenant.domain.events.TenantEvents;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class TenantServiceImplTest {
    public static final int APARTMENT_ID = 10001;
    public static final String MOBILE = "18657124116";
    TenantServiceImpl tenantService;
    TenantRepositoryImpl tenantRepository;
    EventPublisher eventPublisher;

    @Before
    public void init() {
        tenantRepository = new TenantRepositoryImpl();
        eventPublisher = mock(EventPublisher.class);
        tenantService = new TenantServiceImpl(tenantRepository, eventPublisher);
    }

    @Test
    public void newContact() {
        RentContract rentContact = tenantService.newContract(RentContract.builder()
                .apartmentId(APARTMENT_ID)
                .start(System.currentTimeMillis() - 1)
                .end(System.currentTimeMillis() + 1000000)
                .build());
        assertTrue(rentContact.isActive());
        assertTrue(rentContact.isValid());

    }

    @Test
    public void newContact_repeat() {
        RentContract rentContact = tenantService.newContract(RentContract.builder()
                .apartmentId(APARTMENT_ID)
                .start(System.currentTimeMillis() - 1)
                .end(System.currentTimeMillis() + 1000000)
                .build());
        assertTrue(rentContact.isActive());
        assertTrue(rentContact.isValid());
        try {
            rentContact = tenantService.newContract(RentContract.builder()
                    .apartmentId(APARTMENT_ID)
                    .start(System.currentTimeMillis() - 1)
                    .end(System.currentTimeMillis() + 1000000)
                    .build());
            assertTrue(false);
        } catch (DuplicateContactException e) {

        }
    }


    @Test
    public void stopContact() {
        RentContract rentContact = tenantService.newContract(RentContract.builder()
                .apartmentId(APARTMENT_ID)
                .start(System.currentTimeMillis() - 100000)
                .end(System.currentTimeMillis())
                .build());
        Tenant tenant = Tenant.builder()
                .rentContact(rentContact)
                .name("name")
                .lockRole(0)
                .mobile(MOBILE)
                .build();
        tenantService.newTenant(tenant);
        tenantService.stopContract(rentContact);
        verify(eventPublisher).publish(TenantEvents.REMOVE_TENANT_EVENT,new RemoveTenantEvent(tenant));
    }

    @Test
    public void renewContact() {
        RentContract rentContact = tenantService.newContract(RentContract.builder()
                .apartmentId(APARTMENT_ID)
                .start(System.currentTimeMillis() - 100000)
                .end(System.currentTimeMillis())
                .build());
        Tenant tenant = Tenant.builder()
                .rentContact(rentContact)
                .name("name")
                .lockRole(0)
                .mobile(MOBILE)
                .build();
        tenantService.newTenant(tenant);
        tenantService.renewContract(rentContact, System.currentTimeMillis() + 100000);
        verify(eventPublisher).publish(TenantEvents.RENEW_CONTRACT,new RenewContractEvent(tenant));
    }

    @Test
    public void addTenant() {
        RentContract rentContact = tenantService.newContract(RentContract.builder()
                .apartmentId(APARTMENT_ID)
                .start(System.currentTimeMillis() - 100000)
                .end(System.currentTimeMillis())
                .build());
        Tenant tenant1 = Tenant.builder()
                .rentContact(rentContact)
                .name("name")
                .lockRole(0)
                .mobile(MOBILE)
                .build();
        tenantService.newTenant(tenant1);
        verify(eventPublisher).publish(TenantEvents.NEW_TENANT_EVENT, new NewTenantEvent(tenant1));
        try {
            Tenant tenant2 = Tenant.builder()
                    .rentContact(rentContact)
                    .name("name")
                    .mobile(MOBILE)
                    .lockRole(0)
                    .build();
            tenantService.newTenant(tenant2);
            fail();
        } catch (DuplicateTenantException e) {

        }
    }

    @Test
    public void addTenant_same_name() {
        RentContract rentContact = tenantService.newContract(RentContract.builder()
                .apartmentId(APARTMENT_ID)
                .start(System.currentTimeMillis() - 100000)
                .end(System.currentTimeMillis())
                .build());
        tenantService.newTenant(Tenant.builder()
                .rentContact(rentContact)
                .name("name")
                .lockRole(0)
                .mobile(MOBILE)
                .build());
        try {
            tenantService.newTenant(Tenant.builder()
                    .rentContact(rentContact)
                    .name("name")
                    .mobile("153365864448")
                    .lockRole(0)
                    .build());
            fail();
        } catch (DuplicateTenantException e) {

        }
    }

    @Test
    public void addTenant_null_mobile() {
        RentContract rentContact = tenantService.newContract(RentContract.builder()
                .apartmentId(APARTMENT_ID)
                .start(System.currentTimeMillis() - 100000)
                .end(System.currentTimeMillis())
                .build());
        tenantService.newTenant(Tenant.builder()
                .rentContact(rentContact)
                .name("name")
                .lockRole(0)
                .build());
        tenantService.newTenant(Tenant.builder()
                .rentContact(rentContact)
                .name("name")
                .lockRole(0)
                .build());
    }

    @Test
    public void addTenant_empty_mobile() {
        RentContract rentContact = tenantService.newContract(RentContract.builder()
                .apartmentId(APARTMENT_ID)

                .start(System.currentTimeMillis() - 100000)
                .end(System.currentTimeMillis())
                .build());
        tenantService.newTenant(Tenant.builder()
                .rentContact(rentContact)
                .name("name")
                .mobile("")
                .lockRole(0)
                .build());
        tenantService.newTenant(Tenant.builder()
                .rentContact(rentContact)
                .name("name")
                .mobile("")
                .lockRole(0)
                .build());
    }
}
