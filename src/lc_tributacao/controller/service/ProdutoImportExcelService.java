package lc_tributacao.controller.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lc_tributacao.model.entities.Produto;
import lc_tributacao.view.TelaInicial;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

/**
 *
 * @author Rafael Nunes
 */
public class ProdutoImportExcelService {

    private final String path;

    public ProdutoImportExcelService(String path) throws IOException {
        this.path = path;
    }

    public List<Produto> getProdutosDoArquivoExcel() throws IOException, FileNotFoundException, NumberFormatException, IllegalStateException {
        return listaProdutos();
    }

    private List<Produto> listaProdutos() throws IOException, FileNotFoundException, NumberFormatException, IllegalStateException {
        List<Produto> produtos = new ArrayList<>();

        HSSFWorkbook workbook = lerArquivo(path);
        Sheet sheet = workbook.getSheetAt(0); // Primeira planilha

        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {

            Row row = rowIterator.next();
            if (row.getRowNum() == 0) { // Pula o cabeçalho da planilha
                continue;
            }

            Produto produto = parseLinha(row);
            produtos.add(produto);
        }

        TelaInicial.getLog("\n**** LOG ****\nProdutos na planilha: " + produtos.size());

        return produtos;
    }

    private HSSFWorkbook lerArquivo(String filePath) throws IOException {
        FileInputStream inputStream = new FileInputStream(filePath);
        return new HSSFWorkbook(inputStream, true);
    }

    private Produto parseLinha(Row row) {
        Produto produto = new Produto();

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
        }

        if (cstCell != null) {
            cstCell.setCellType(CellType.STRING);
            String cst = cstCell.getStringCellValue();
            produto.setCst(getCstFormatado(cst));
        } else {
            produto.setCst("");
        }

        if (cfopCell != null) {
            cfopCell.setCellType(CellType.STRING);
            produto.setCfop(cfopCell.getStringCellValue());
        } else {
            produto.setCfop("");
        }

        if (ncmCell != null) {
            ncmCell.setCellType(CellType.STRING);
            String ncm = ncmCell.getStringCellValue();
            produto.setNcm(getNcmFormatado(ncm));
        } else {
            produto.setNcm("");
        }

        if (cestCell != null) {
            cestCell.setCellType(CellType.STRING);
            String cest = cestCell.getStringCellValue();
            produto.setCest(getCestFormatado(cest));
        } else {
            produto.setCest("");
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
            if (genero.length() >= 2) {
                produto.setGenero(genero.substring(0, 2));
            } else {
                produto.setGenero("");
            }
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
        if (doubleValueCell == null) {
            return null;
        }

        String cellValue = "";
        if (doubleValueCell.getCellType() == CellType.STRING) {
            cellValue = doubleValueCell.getStringCellValue().trim();

            if (cellValue.contains(",") && cellValue.contains(".")) {
                cellValue = cellValue.replace(".", "").replace(",", ".");
            } else if (cellValue.contains(",")) {
                cellValue = cellValue.replace(",", ".");
            }

        } else if (doubleValueCell.getCellType() == CellType.NUMERIC) {
            cellValue = String.valueOf(doubleValueCell.getNumericCellValue());
        }

        return Double.parseDouble(cellValue);
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

    private String getCstFormatado(String cst) {
        String cstValue;
        switch (cst.trim().length()) {
            case 1:
                cstValue = "0" + cst.trim();
                break;
            default:
                cstValue = cst.trim();
                break;
        }
        return cstValue;
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
}
