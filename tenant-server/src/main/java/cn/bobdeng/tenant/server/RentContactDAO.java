package cn.bobdeng.tenant.server;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RentContactDAO extends CrudRepository<RentContractDO,Integer> {
    Optional<RentContractDO> findTop1ByApartmentIdOrderByStartDesc(int apartmentId);
}
