package lc_tributacao.controller.conexao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import lc_tributacao.controller.conexao.exceptions.Exceptions;

/**
 *
 * @author Rafael Nunes
 */
public class GenericMysqlDAO {

    final private String driver = "com.mysql.jdbc.Driver";
    private Connection conn = null;
    public static String dataBase;
    public static String usuario;
    public static String senha;
    private String porta;

    public GenericMysqlDAO() throws IOException {
        setarLoginBanco(); // Na instanciação da Classe ja executa esse metodo;
    }

    private void setarLoginBanco() throws FileNotFoundException, IOException {
        File file = new File(System.getProperty("user.dir") + "\\rede.txt");

        if (!file.exists()) {
            System.out.println("Arquivo rede.txt inexistente.");
        } else if (file.length() == 0) {
            System.out.println("Arquivo rede.txt vazio.");
        } else {

            String linha;
            try (FileReader fileReader = new FileReader(file);
                    BufferedReader reader = new BufferedReader(fileReader)) {

                while ((linha = reader.readLine()) != null) {

                    String[] split = linha.split(":");

                    if (split[0].equalsIgnoreCase("DB")) {
                        dataBase = (linha.split(":")[1]);
                    }
                    if (split[0].equalsIgnoreCase("USER")) {
                        usuario = (linha.split(":")[1]);
                    }
                    if (split[0].equalsIgnoreCase("KEY")) {
                        senha = (linha.split(":")[1]);
                    }
                    if (split[0].equalsIgnoreCase("PORT")) {
                        porta = (linha.split(":")[1]);
                    }
                }
            }
        }
    }

    public Connection getConnection() throws Exception {
        if (conn == null) {
            abrirConexao();
        }
        return conn;
    }

    private void abrirConexao() throws ClassNotFoundException, SQLException {
        try {
            String url = "jdbc:mysql://localhost:" + porta + "/" + dataBase + "?useUnicode=true&characterEncoding=UTF-8";
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexao Mysql efetuada com sucesso!");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.getMessage();
            JOptionPane.showMessageDialog(null, "\nNão Foi Possivel Conctar ao Servidor\nVerifique a Conexão com o banco de dados\n\n\n" + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }

    public static void closeConnection(Connection conn) throws SQLException {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexao Mysql fechada com sucesso!");
            } catch (SQLException e) {
                System.out.println("Error! closeConnection " + e.getMessage());
                throw new Exceptions(e.getMessage());
            }
        }
    }
}
