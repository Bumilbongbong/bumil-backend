package com.example.bumil_backend.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class DataSourceShutdown {

    private final HikariDataSource dataSource;

    public DataSourceShutdown(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PreDestroy
    public void close() {
        dataSource.close();
    }
}