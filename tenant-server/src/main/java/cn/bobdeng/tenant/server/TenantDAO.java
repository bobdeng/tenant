package cn.bobdeng.tenant.server;

import cn.bobdeng.tenant.domain.Tenant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface TenantDAO extends CrudRepository<TenantDO,Long> {

    Stream<TenantDO> findByRentContactId(int contactId);

    Optional<TenantDO> findByRentContactIdAndMobile(int contactId, String mobile);
}
