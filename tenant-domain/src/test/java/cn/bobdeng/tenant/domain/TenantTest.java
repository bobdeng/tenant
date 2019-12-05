package cn.bobdeng.tenant.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class TenantTest {

    @Test
    public void test_has_no_lock_role(){
        Tenant tenant = Tenant.builder()
                .name("name")
                .lockRole(0)
                .build();
        assertFalse(tenant.hasLockRole());
    }

    @Test
    public void test_has_lock_role(){
        Tenant tenant = Tenant.builder()
                .name("name")
                .lockRole(50)
                .build();
        assertTrue(tenant.hasLockRole());
    }
}
