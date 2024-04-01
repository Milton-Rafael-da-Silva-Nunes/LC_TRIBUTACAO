package lc_tributacao.util;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author Rafael Nunes
 */
public class BarrasDeProgresso {

    public static void updateProgressBar(final JProgressBar progressBar, final int progress, final String description) {
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                progressBar.setValue(progress);
                progressBar.setString(description);
            }
        });
    }
}
