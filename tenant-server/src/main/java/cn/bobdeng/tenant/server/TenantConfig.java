package cn.bobdeng.tenant.server;

import cn.bobdeng.tenant.domain.TenantRepository;
import cn.bobdeng.tenant.domain.TenantService;
import cn.bobdeng.tenant.domain.TenantServiceImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("cn.bobdeng.tenant.server")
@EnableJpaRepositories("cn.bobdeng.tenant.server")
public class TenantConfig {
    @Bean
    public TenantService tenantService(TenantRepository tenantRepository) {
        return new TenantServiceImpl(tenantRepository);
    }
}
