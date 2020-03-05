package cn.bobdeng.tenant.domain.events;

import cn.bobdeng.tenant.domain.Tenant;
import lombok.Data;

@Data
public class RemoveTenantEvent {
    private long id;
    private int apartment;

    public RemoveTenantEvent(Tenant tenant) {
        this.id = tenant.getId();
        this.id = tenant.getRentContact().getApartmentId();
    }
}
