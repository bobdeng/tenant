package cn.bobdeng.tenant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentContact {
    private int id;
    private String representName;
    private String representMobile;
    private String contactId;
    private int apartmentId;
    private boolean active;
    private long start;
    private long end;

    @Transient
    public boolean isValid() {
        return start < System.currentTimeMillis() && end > System.currentTimeMillis();
    }
}
