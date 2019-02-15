package cn.bobdeng.tenant.server;

import cn.bobdeng.tenant.domain.RentContract;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_apartment_rent_contract")
public class RentContractDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 20)
    private String representName;
    @Column(length = 20)
    private String representMobile;
    @Column(length = 50)
    private String contractId;
    private int apartmentId;
    private boolean active;
    private long start;
    private long end;

    public RentContract toEntity() {
        return RentContract.builder()
                .id(getId())
                .representName(getRepresentName())
                .representMobile(getRepresentMobile())
                .contractId(getContractId())
                .apartmentId(getApartmentId())
                .active(isActive())
                .start(getStart())
                .end(getEnd())
                .build();
    }

    public static RentContractDO fromEntity(RentContract entity) {
        return RentContractDO.builder()
                .id(entity.getId())
                .representName(entity.getRepresentName())
                .representMobile(entity.getRepresentMobile())
                .contractId(entity.getContractId())
                .apartmentId(entity.getApartmentId())
                .active(entity.isActive())
                .start(entity.getStart())
                .end(entity.getEnd())
                .build();
    }
}
