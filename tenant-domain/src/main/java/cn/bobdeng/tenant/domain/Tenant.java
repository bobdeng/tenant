package cn.bobdeng.tenant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tenant {
    private long id;
    private String name;
    private String mobile;
    private RentContact rentContact;
}
