package lc_tributacao.util;

import javax.swing.ImageIcon;
import static lc_tributacao.view.TelaInicial.lblImg;

/**
 *
 * @author Rafael Nunes
 */
public class LogoPrincipal {

    public static void getLogoPrincipal() {
        ImageIcon icon = new ImageIcon("src/lc_tributacao/imagem/novaLogoLC.gif"); // novaLogoLC.gif
        icon.setImage(icon.getImage().getScaledInstance(158, 48, 15));
        lblImg.setIcon(icon);
    }
}
