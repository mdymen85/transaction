package com.platform.transactions.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
@Slf4j
public class RedissonConfig {

    @Value("${application.lockmanager.port:6379}")
    private Integer port;

    @Value("${application.lockmanager.host:localhost}")
    private String serverHost;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(serverHost, port));
    }

    @Bean
    public RedissonClient redisson() {
        Config config = new Config();
        config.useSingleServer().setAddress(this.getUrlRedis());
        return Redisson.create(config);
    }

    private String getUrlRedis() {
        StringBuilder sb = new StringBuilder("redis");
        sb.append(":");
        sb.append("//");
        sb.append(serverHost);
        sb.append(":");
        sb.append(port);

        log.info("URI Redis: {}.", sb.toString());

        return sb.toString();
    }

}
