package com.example;

//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class TestCode {
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    void testMainMethod() {

    }

    @Test
    void testInsertUserData() throws SQLException {
        // Mock Connection and PreparedStatement
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

        // Simulate DriverManager behavior
        DriverManager.setLogWriter(null);
        mockStaticDriverManager(mockConnection);

        // Invoke method
        Code.insertUserData("jdbc:mysql://localhost:3306/testdb", "user", "password", "1", "John");

        // Verify SQL execution
        verify(mockConnection).prepareStatement(anyString());
        verify(mockPreparedStatement).executeUpdate();

        // Verify resources are closed
        verify(mockPreparedStatement).close();
        verify(mockConnection).close();
    }

    private void mockStaticDriverManager(Connection mockConnection) throws SQLException {
        DriverManager.setLogWriter(null); // Suppress warnings
        when(DriverManager.getConnection(anyString(), anyString(), anyString())).thenReturn(mockConnection);
    }
}
