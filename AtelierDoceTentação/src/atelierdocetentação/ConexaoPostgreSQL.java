/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atelierdocetentação;

/**
 *
 * @author Paulo
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPostgreSQL {
    private static final String URL = "jdbc:postgresql://localhost:5432/atelierdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    private Connection connection;

    public ConexaoPostgreSQL() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão bem-sucedida!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados!");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexão fechada!");
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão com o banco de dados!");
            }
        }
    }

    public static void main(String[] args) {
        ConexaoPostgreSQL conexao = new ConexaoPostgreSQL();
        conexao.close();
    }
}
