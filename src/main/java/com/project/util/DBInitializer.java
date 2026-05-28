package com.project.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Runs init.sql on application startup to create tables if they don't exist.
 * This ensures the app works on Railway (cloud) without manual DB setup.
 */
@WebListener
public class DBInitializer implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(DBInitializer.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("DBInitializer: Running database initialization...");
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("sql/init.sql")) {
            if (is == null) {
                LOGGER.warning("init.sql not found, skipping DB init.");
                return;
            }
            String sql = new BufferedReader(new InputStreamReader(is))
                    .lines().collect(Collectors.joining("\n"));

            // Split by semicolon and run each statement
            String[] statements = sql.split(";");
            try (Connection conn = DBConnection.getConnection();
                 Statement stmt = conn.createStatement()) {
                for (String statement : statements) {
                    String trimmed = statement.trim();
                    if (!trimmed.isEmpty() && !trimmed.startsWith("--")) {
                        stmt.execute(trimmed);
                    }
                }
                LOGGER.info("DBInitializer: Database initialized successfully.");
            }
        } catch (IOException | SQLException e) {
            LOGGER.log(Level.SEVERE, "DBInitializer: Failed to initialize database.", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Nothing to clean up
    }
}
