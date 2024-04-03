package lc_tributacao.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Rafael Nunes
 */
public class DataHora {
    
    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("ddMMyyyHHmmss");
    
    public static String getDataHoraAtual() {
        Date dataHoraAtual = new Date();
        return sdf1.format(dataHoraAtual);
    }
    
    public static String getDataHoraAtualFormatoBackup() {
        Date dataHoraAtual = new Date();
        return sdf2.format(dataHoraAtual);
    }
}
