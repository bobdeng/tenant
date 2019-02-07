package cn.bobdeng.tenant.server;

import cn.bobdeng.tenant.domain.RentContact;
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
@Table(name = "t_apartment_rent_contact")
public class RentContactDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 20)
    private String representName;
    @Column(length = 20)
    private String representMobile;
    @Column(length = 50)
    private String contactId;
    private int apartmentId;
    private boolean active;
    private long start;
    private long end;

    public RentContact toEntity() {
        return RentContact.builder()
                .id(getId())
                .representName(getRepresentName())
                .representMobile(getRepresentMobile())
                .contactId(getContactId())
                .apartmentId(getApartmentId())
                .active(isActive())
                .start(getStart())
                .end(getEnd())
                .build();
    }

    public static RentContactDO fromEntity(RentContact entity) {
        return RentContactDO.builder()
                .id(entity.getId())
                .representName(entity.getRepresentName())
                .representMobile(entity.getRepresentMobile())
                .contactId(entity.getContactId())
                .apartmentId(entity.getApartmentId())
                .active(entity.isActive())
                .start(entity.getStart())
                .end(entity.getEnd())
                .build();
    }
}
