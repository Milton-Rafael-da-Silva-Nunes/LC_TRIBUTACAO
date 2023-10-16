package lc_tributacao.controller.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lc_tributacao.model.entities.Produtos;
import lc_tributacao.view.TelaInicial;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

/**
 *
 * @author MIGRAÇÃO
 */
public class ProdutosImportService {

    public List<Produtos> getProdutosExcel(String path) throws IOException {
        return listaProdutos(path);
    }

    private List<Produtos> listaProdutos(String filePath) throws IOException {
        List<Produtos> produtos = new ArrayList<>();

        try {
            HSSFWorkbook workbook = lerArquivo(filePath);
            Sheet sheet = workbook.getSheetAt(0); // Primeira planilha

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == 0) { // Pula o cabeçalho da planilha
                    continue;
                }
                Produtos produto = parseLinha(row);
                produtos.add(produto);
            }
            TelaInicial.getLogError("\nProdutos da planilha: " + contarLinhasExcel(filePath) + "\n");
        } catch (FileNotFoundException e) {
            TelaInicial.getLogError("\nArquivo não encontrado: " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            TelaInicial.getLogError("\nFormato numerico invalido: " + e.getMessage() + "\n");
        } catch (IllegalStateException e) {
            TelaInicial.getLogError("Erro geral: " + e.getMessage());
        }
        return validarObjetoProdutos(produtos);
    }

    private HSSFWorkbook lerArquivo(String filePath) throws IOException {
        FileInputStream inputStream = new FileInputStream(filePath);
        return new HSSFWorkbook(inputStream, true);
    }

    private Produtos parseLinha(Row row) {
        Produtos produto = new Produtos();

        Cell idProdutoCell = row.getCell(0);
        Cell barrasCell = row.getCell(1);
        Cell nomeCell = row.getCell(2);
        Cell cstCell = row.getCell(3);
        Cell cfopCell = row.getCell(4);
        Cell ncmCell = row.getCell(5);
        Cell cestCell = row.getCell(6);
        Cell pisCell = row.getCell(7);
        Cell cofinsCell = row.getCell(8);
        Cell ipiCell = row.getCell(9);
        Cell origemCell = row.getCell(10);
        Cell pisAliqCell = row.getCell(11);
        Cell cofinsAliqCell = row.getCell(12);
        Cell ipiAliqCell = row.getCell(13);
        Cell icmsAliqCell = row.getCell(14);
        Cell icmsAliqRedBcCell = row.getCell(15);

        if (idProdutoCell != null) {
            idProdutoCell.setCellType(CellType.STRING);
            produto.setIdProduto(returnIntegerValue(idProdutoCell));
        } else {
            produto.setIdProduto(0);
            TelaInicial.getLogError("ID_PRODUTO invalido: " + row.getCell(0) + " - " + nomeCell);
        }

        if (barrasCell != null) {
            barrasCell.setCellType(CellType.STRING);
            produto.setBarras(barrasCell.getStringCellValue());
        } else {
            produto.setBarras("");
        }

        if (nomeCell != null) {
            nomeCell.setCellType(CellType.STRING);
            produto.setNome(nomeCell.getStringCellValue());
        } else {
            produto.setNome("");
            TelaInicial.getLogError("NOME invalido (id): " + idProdutoCell + " " + nomeCell);
        }

        if (cstCell != null) {
            cstCell.setCellType(CellType.STRING);
            produto.setCst(cstCell.getStringCellValue());
        } else {
            produto.setCst("");
            TelaInicial.getLogError("CST invalido (id): " + idProdutoCell + " " + nomeCell);
        }

        if (cfopCell != null) {
            cfopCell.setCellType(CellType.STRING);
            produto.setCfop(cfopCell.getStringCellValue());
        } else {
            produto.setCfop("");
            TelaInicial.getLogError("CFOP invalido (id): " + idProdutoCell + " " + nomeCell);
        }

        if (ncmCell != null) {
            ncmCell.setCellType(CellType.STRING);
            String ncm = ncmCell.getStringCellValue();
            produto.setNcm(getNcmFormatado(ncm));
        } else {
            produto.setNcm("");
            TelaInicial.getLogError("NCM invalido (id): " + idProdutoCell + " " + nomeCell);
        }

        if (cestCell != null) {
            cestCell.setCellType(CellType.STRING);
            String cest = cestCell.getStringCellValue();
            produto.setCest(getCestFormatado(cest));
        } else {
            produto.setCest("");
            TelaInicial.getLogError("CEST invalido (id): " + idProdutoCell + " " + nomeCell);
        }

        if (pisCell != null) {
            pisCell.setCellType(CellType.STRING);
            produto.setPis(getPisCofinsIpiFormatado(pisCell.getStringCellValue()));
        } else {
            produto.setPis("07");
        }

        if (cofinsCell != null) {
            cofinsCell.setCellType(CellType.STRING);
            produto.setCofins(getPisCofinsIpiFormatado(cofinsCell.getStringCellValue()));
        } else {
            produto.setCofins("07");
        }

        if (ipiCell != null) {
            ipiCell.setCellType(CellType.STRING);
            produto.setIpi(getPisCofinsIpiFormatado(ipiCell.getStringCellValue()));
        } else {
            produto.setIpi("");
        }

        if (origemCell != null) {
            origemCell.setCellType(CellType.STRING);
            produto.setOrigem(origemCell.getStringCellValue());
        } else {
            produto.setOrigem("0");
        }

        if (ncmCell != null) {
            ncmCell.setCellType(CellType.STRING);
            String genero = getNcmFormatado(ncmCell.getStringCellValue());
            produto.setGenero(genero.substring(0, 2));
        } else {
            produto.setGenero("");
        }

        if (pisAliqCell != null) {
            produto.setPisAliq(returnDoubleValue(pisAliqCell));
        } else {
            produto.setPisAliq(0.0);
        }

        if (cofinsAliqCell != null) {
            produto.setCofinsAliq(returnDoubleValue(cofinsAliqCell));
        } else {
            produto.setCofinsAliq(0.0);
        }

        if (ipiAliqCell != null) {
            produto.setIpiAliq(returnDoubleValue(ipiAliqCell));
        } else {
            produto.setIpiAliq(0.0);
        }

        if (icmsAliqCell != null) {
            produto.setIcmsAliq(returnDoubleValue(icmsAliqCell));
        } else {
            produto.setIcmsAliq(0.0);
        }

        if (icmsAliqRedBcCell != null) {
            produto.setIcmsAliqRedBc(returnDoubleValue(icmsAliqRedBcCell));
        } else {
            produto.setIcmsAliqRedBc(0.0);
        }

        return produto;
    }

    private Integer returnIntegerValue(Cell integerValueCell) {
        int integerValue = 0;
        if (integerValueCell.getCellType() == CellType.STRING) {
            integerValue = Integer.parseInt(integerValueCell.getStringCellValue().trim());
        } else if (integerValueCell.getCellType() == CellType.NUMERIC) {
            integerValue = (int) integerValueCell.getNumericCellValue();
        }
        return integerValue;
    }

    private Double returnDoubleValue(Cell doubleValueCell) {
        double doubleValue = 0.0;
        if (doubleValueCell.getCellType() == CellType.STRING) {
            doubleValue = Double.parseDouble(doubleValueCell.getStringCellValue().trim());
        } else if (doubleValueCell.getCellType() == CellType.NUMERIC) {
            doubleValue = doubleValueCell.getNumericCellValue();
        }
        return doubleValue;
    }

    private String getNcmFormatado(String ncm) {
        String ncmValue;
        switch (ncm.trim().length()) {
            case 7:
                ncmValue = "0" + ncm.trim();
                break;
            case 6:
                ncmValue = "00" + ncm.trim();
                break;
            default:
                ncmValue = ncm.trim();
                break;
        }
        return ncmValue;
    }

    private String getCestFormatado(String cest) {
        String cestValue;
        switch (cest.trim().length()) {
            case 6:
                cestValue = "0" + cest.trim();
                break;
            case 5:
                cestValue = "00" + cest.trim();
                break;
            default:
                cestValue = cest.trim();
                break;
        }
        return cestValue;
    }

    private String getPisCofinsIpiFormatado(String pisCofinsIpi) {
        String pisCofinsIpiValue;
        switch (pisCofinsIpi.trim().length()) {
            case 1:
                pisCofinsIpiValue = "0" + pisCofinsIpi.trim();
                break;
            default:
                pisCofinsIpiValue = pisCofinsIpi.trim();
                break;
        }
        return pisCofinsIpiValue;
    }

    private List<Produtos> validarObjetoProdutos(List<Produtos> listaProdutos) {
        List<Produtos> produtosValidados = new ArrayList<>();

        for (Produtos produto : listaProdutos) {
            if (produto == null || produto.getIdProduto() == null || produto.getIdProduto() == 0 || produto.getNome().isEmpty() || produto.getCst() == null
                    || produto.getCfop() == null || produto.getNcm() == null || produto.getCest() == null || produto.getPis() == null
                    || produto.getCofins() == null || produto.getPisAliq() == null || produto.getCofinsAliq() == null || produto.getIcmsAliq() == null) {
                TelaInicial.getLogError("******** PRODUTOS INVALIDOS ********");
                TelaInicial.getLogError("--> " + produto);
            } else {
                produtosValidados.add(produto);
                System.out.println("Produtos --> " + produto);
            }
        }
        return produtosValidados;
    }

    private int contarLinhasExcel(String filePath) {
        int totalLinhas = 0;

        try {
            HSSFWorkbook workbook = lerArquivo(filePath);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == 0) {
                    continue;
                }

                Produtos produto = parseLinha(row);
                if (produto != null && produto.getIdProduto() != null && !"0".equals(produto.getIdProduto()) && !produto.getNome().isEmpty()) {
                    totalLinhas++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Metodo contador de linhas --> " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Metodo contador de linhas --> " + e.getMessage());
        }

        return totalLinhas;
    }
}
