package cn.bobdeng.tenant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tenant {
    private long id;
    private String name;
    private String mobile;
    private String faceImage;
    private String comCode;
    private int lockRole;
    private RentContract rentContact;

    public boolean hasLockRole() {
        return lockRole > 0;
    }

    public boolean isAdmin() {
        return lockRole >= 100;
    }

    public boolean isSame(Tenant tenant) {
        return Objects.equals(name,tenant.getName()) || Objects.equals(mobile,tenant.getMobile()
        );
    }
}
