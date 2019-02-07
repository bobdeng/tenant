package cn.bobdeng.tenant.server;

import org.springframework.data.repository.CrudRepository;

public interface TenantDAO extends CrudRepository<TenantDO,Long> {
}
