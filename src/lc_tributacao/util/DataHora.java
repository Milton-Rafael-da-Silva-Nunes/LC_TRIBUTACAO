package lc_tributacao.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Rafael Nunes
 */
public class DataHora {
    
    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final Date dataHoraAtual = new Date();
    
    public static String getDataHoraAtual() {
        return sdf1.format(dataHoraAtual);
    }
}
