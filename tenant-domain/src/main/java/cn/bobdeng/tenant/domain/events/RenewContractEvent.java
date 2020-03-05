package cn.bobdeng.tenant.domain.events;

import cn.bobdeng.tenant.domain.Tenant;
import lombok.Data;

@Data
public class RenewContractEvent {
    private long start;
    private long end;
    private String name;
    private String face;
    private long id;

    public RenewContractEvent(Tenant tenant) {
        this.id = tenant.getId();
        this.name = tenant.getName();
        this.face = tenant.getFaceImage();
        this.start = tenant.getRentContact().getStart();
        this.end = tenant.getRentContact().getEnd();
    }
}
