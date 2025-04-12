package com.interbank.demo.antifraudservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class DatabaseTestService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseTestService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String testConnection() {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            if (connection != null && !connection.isClosed()) {
                return "Conexión exitosa a la base de datos PostgreSQL";
            } else {
                return "No se pudo establecer la conexión con la base de datos";
            }
        } catch (SQLException e) {
            return "Error al intentar conectar a la base de datos: " + e.getMessage();
        }
    }
}

