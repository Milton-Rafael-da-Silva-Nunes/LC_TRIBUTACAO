package lc_tributacao.view;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import lc_tributacao.controller.conexao.GenericMysqlDAO;
import static lc_tributacao.controller.conexao.GenericMysqlDAO.dataBase;
import lc_tributacao.model.dao.ProdutoDao;
import lc_tributacao.controller.service.BancoDadosService;
import lc_tributacao.controller.service.ProdutoExportExcelService;
import lc_tributacao.controller.service.ProdutoImportExcelService;
import lc_tributacao.model.dao.GrupoTributacaoDao;
import lc_tributacao.model.dao.CestDao;
import lc_tributacao.model.dao.EmpresaDao;
import lc_tributacao.model.dao.NcmDao;
import lc_tributacao.model.entities.Cest;
import lc_tributacao.model.entities.Empresa;
import lc_tributacao.model.entities.GrupoTributacao;
import lc_tributacao.model.entities.Ncm;
import lc_tributacao.model.entities.Produto;
import static lc_tributacao.util.Versao.getVersaoPrograma;

/**
 *
 * @author Rafael Nunes
 */
public class TelaInicial extends javax.swing.JFrame {

    String filePath = "";
    Connection conn = new GenericMysqlDAO().getConnection();

    public TelaInicial() throws Exception {
        initComponents();
        imagemLc();
        lblCaminhoArq.setText(" Ex: Documentos\\AJUSTE - TRIBUTARIO.xls");
        lblCaminhoArq.setForeground(Color.GRAY);
    }

    private void imagemLc() {
        ImageIcon icon = new ImageIcon("C:/LC sistemas - Softhouse/lib/lc_logoSofthouseBrasao.gif");
        icon.setImage(icon.getImage().getScaledInstance(25, 25, 15));
        lblImg.setIcon(icon);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblImg = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblCaminhoArq = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLogError = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        btnImportar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnExportar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(getVersaoPrograma());
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("LC Ajuste Tributário");

        lblImg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(272, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(lblImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Local do arquivo:");

        jPanel2.setBackground(new java.awt.Color(255, 255, 0));

        lblCaminhoArq.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        lblCaminhoArq.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCaminhoArq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCaminhoArq, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel3.setFont(new java.awt.Font("Dialog", 3, 9)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText(" ID_PRODUTO / BARRAS / NOME / CST / CFOP / NCM / CEST / PIS / COFINS / IPI / ORIGEM / PIS_ALIQ / COFINS_ALIQ / IPI_ALIQ / ICMS_ALIQ / ICMS_ALIQ_RED_BC");

        txtLogError.setColumns(20);
        txtLogError.setRows(5);
        jScrollPane1.setViewportView(txtLogError);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Log:");

        btnImportar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnImportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lc_tributacao/imagem/check.png"))); // NOI18N
        btnImportar.setText("Importar");
        btnImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 3, 9)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Padrões de Colunas:");

        btnExportar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lc_tributacao/imagem/export-excel.png"))); // NOI18N
        btnExportar.setText("Exportar");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnImportar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnImportar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)))
        );

        setSize(new java.awt.Dimension(952, 586));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarActionPerformed
        if (chamarTelaImportar()) {
            criarTabelaTemp();
            importarProdutosDoExcel();
            JOptionPane.showMessageDialog(null, "Produtos atualizados com sucesso!", getVersaoPrograma(), JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "<html><b>Atenção</b>...<br>Selecione um arquivo para iniciar!</html>", getVersaoPrograma(), JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnImportarActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        if (chamarTelaExportar()) {
            if (podeExportarProdutosXls()) {
                JOptionPane.showMessageDialog(null, "Tabela exportada com sucesso! \n\n<html><b>Caminho:</b> " + filePath + "\\CLASSIFICAO DE TRIBUTOS.xls</html>", getVersaoPrograma(), JOptionPane.INFORMATION_MESSAGE);
            } else {
                getLog("**** ATENÇÃO ****\nNenhum produto encontrado na base '" + dataBase + "'");
            }
        } else {
            JOptionPane.showMessageDialog(null, "<html><b>Atenção</b>...<br>Selecione um caminho para Exportar!</html>", "Atenção", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnExportarActionPerformed

    private boolean chamarTelaImportar() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivos Excel", "xls"));
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            filePath = selectedFile.getAbsolutePath();
            lblCaminhoArq.setText(" " + filePath);
            lblCaminhoArq.setForeground(Color.GRAY);
            System.out.println("Arquivo Excel selecionado: " + filePath);
            return true;
        } else {
            System.out.println("Nenhum arquivo selecionado.");
        }
        return false;
    }

    private boolean chamarTelaExportar() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            filePath = selectedFile.getAbsolutePath();
            lblCaminhoArq.setText(" " + filePath + "\\CLASSIFICAO DE TRIBUTOS.xls");
            lblCaminhoArq.setForeground(Color.GRAY);
            System.out.println("Pasta de exportação selecionada: " + filePath);
            return true;
        } else {
            System.out.println("Nenhuma pasta selecionada.");
        }
        return false;
    }

    private void importarProdutosDoExcel() {
        if (validarArquivoExcel()) {
            try {
                ProdutoDao prodDao = new ProdutoDao(conn);
                ProdutoImportExcelService prodServic = new ProdutoImportExcelService();
                List<Produto> listaDeProdutos = prodServic.getProdutosExcel(filePath);

                prodDao.InserirProdutosNaTabelaTemp(listaDeProdutos);
                prodDao.inserirNovosGruposDeTributacaoBancoPrincipal(obterGruposTributacaoComBaseNosProdutosDaEmpresa(listaDeProdutos));
                prodDao.inserirNovosCESTs(obterCestComBaseNosProdutos(listaDeProdutos));
                prodDao.inserirNovosNCMs(obterNcmComBaseNosProdutos(listaDeProdutos));
                prodDao.executarAcoesNoBancoPrincipal();
            } catch (SQLException | IOException ex) {
                getLog("\n**** ATENÇÃO **** \n" + ex.getMessage());
            }
        }
    }

    private List<GrupoTributacao> obterGruposTributacaoComBaseNosProdutosDaEmpresa(List<Produto> listaDeProdutos) throws SQLException {
        int idEmpresa = 1; // valor padrao "POR ENQUANTO"
        Empresa empresa = new EmpresaDao(conn).getEmpresa(idEmpresa);
        return new GrupoTributacaoDao(conn).obterGruposTributacaoComBaseNaLocalidadeDaEmpresa(listaDeProdutos, empresa);
    }
    
    private List<Cest> obterCestComBaseNosProdutos(List<Produto> listaDeProdutos) throws SQLException {
        return new CestDao(conn).obterCestsComBaseNosProdutos(listaDeProdutos);
    }
    
    private List<Ncm> obterNcmComBaseNosProdutos(List<Produto> listaDeProdutos) throws SQLException {
        return new NcmDao(conn).obterCestsComBaseNosProdutos(listaDeProdutos);
    }

    private void criarTabelaTemp() {
        BancoDadosService bd = null;
        boolean deletarTabelaTemp = true;
        try {
            bd = new BancoDadosService(conn, deletarTabelaTemp);
            bd.backupTabelasProdutosEGrupoTributacaoBancoPrincipal(); // Backup de segurança
            bd.criarTabelaTributacaoTemp();
        } catch (SQLException | IOException | InterruptedException e) {
            getLog("\n**** ATENÇÃO ****\n" + e.getMessage());
        }
    }

    private boolean podeExportarProdutosXls() {
        if (validarArquivoExcel()) {
            try {
                ProdutoExportExcelService prodExportService = new ProdutoExportExcelService(conn);
                return prodExportService.gerarProdutosXls(filePath);
            } catch (SQLException | IOException e) {
                getLog("\n**** ATENÇÃO ****\n" + e.getMessage());
            }
        }
        return false;
    }

    private boolean validarArquivoExcel() {
        return !filePath.isEmpty();
    }

    public static void getLog(String texto) {
        txtLogError.setText(txtLogError.getText() + texto + "\n");
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            try {
                new TelaInicial().setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnImportar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCaminhoArq;
    private javax.swing.JLabel lblImg;
    private static javax.swing.JTextArea txtLogError;
    // End of variables declaration//GEN-END:variables
}
