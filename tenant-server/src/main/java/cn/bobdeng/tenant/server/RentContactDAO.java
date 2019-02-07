package cn.bobdeng.tenant.server;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RentContactDAO extends CrudRepository<RentContactDO,Integer> {
    Optional<RentContactDO> findTop1ByApartmentIdOrderByStartDesc(int apartmentId);
}
