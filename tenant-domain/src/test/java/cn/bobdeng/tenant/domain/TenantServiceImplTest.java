package cn.bobdeng.tenant.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class TenantServiceImplTest {
    public static final int APARTMENT_ID = 10001;
    public static final String MOBILE = "18657124116";
    TenantServiceImpl tenantService;
    TenantRepositoryImpl tenantRepository;

    @Before
    public void init() {
        tenantRepository = new TenantRepositoryImpl();
        tenantService = new TenantServiceImpl(tenantRepository);
    }

    @Test
    public void newContact() {
        RentContact rentContact = tenantService.newContact(RentContact.builder()
                .apartmentId(APARTMENT_ID)
                .start(System.currentTimeMillis()-1)
                .end(System.currentTimeMillis() + 1000000)
                .build());
        assertTrue(rentContact.isActive());
        assertTrue(rentContact.isValid());
    }
    @Test
    public void newContact_repeat() {
        RentContact rentContact = tenantService.newContact(RentContact.builder()
                .apartmentId(APARTMENT_ID)
                .start(System.currentTimeMillis()-1)
                .end(System.currentTimeMillis() + 1000000)
                .build());
        assertTrue(rentContact.isActive());
        assertTrue(rentContact.isValid());
        try{
            rentContact = tenantService.newContact(RentContact.builder()
                    .apartmentId(APARTMENT_ID)
                    .start(System.currentTimeMillis()-1)
                    .end(System.currentTimeMillis() + 1000000)
                    .build());
            assertTrue(false);
        }catch (DuplicateContactException e){

        }
    }

    @Test
    public void stopContact() {
    }

    @Test
    public void renewContact() {
        RentContact rentContact = tenantService.newContact(RentContact.builder()
                .apartmentId(APARTMENT_ID)
                .start(System.currentTimeMillis()-100000)
                .end(System.currentTimeMillis())
                .build());
        tenantService.renewContact(rentContact,System.currentTimeMillis()+100000);
    }
    @Test
    public void addTenant(){
        RentContact rentContact = tenantService.newContact(RentContact.builder()
                .apartmentId(APARTMENT_ID)
                .start(System.currentTimeMillis()-100000)
                .end(System.currentTimeMillis())
                .build());
        tenantService.newTenant(Tenant.builder()
                .rentContact(rentContact)
                .name("name")
                .mobile(MOBILE)
                .build());
        try{
            tenantService.newTenant(Tenant.builder()
                    .rentContact(rentContact)
                    .name("name")
                    .mobile(MOBILE)
                    .build());
            assertTrue(false);
        }catch (DuplicateTenantException e){

        }
    }
}