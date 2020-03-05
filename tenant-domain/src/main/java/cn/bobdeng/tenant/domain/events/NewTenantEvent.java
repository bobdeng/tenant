package cn.bobdeng.tenant.domain.events;

import cn.bobdeng.tenant.domain.Tenant;
import lombok.Data;

@Data
public class NewTenantEvent {
    private String name;
    private String face;
    private long id;
    private long start;
    private long end;
    private int apartmentId;
    public NewTenantEvent(Tenant tenant) {
        this.id = tenant.getId();
        this.name = tenant.getName();
        this.face = tenant.getFaceImage();
        this.start = tenant.getRentContact().getStart();
        this.end = tenant.getRentContact().getEnd();
        this.apartmentId = tenant.getRentContact().getApartmentId();
    }
}
