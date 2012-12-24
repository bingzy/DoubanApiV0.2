package visual;

/**
 * This program searches and displays book information on Douban Website using Douban API V0.2.
 * known bugs to be fixed:
 * character encoding problems;
 * connection exceptions problems;
 * scroll bar auto scroll problem.
 * @author looya
 *
 */

public class DoubanApi{

	/**
	 * The entrance point of the program. Sets up swing look and feel and calls the main frame.
	 * @param args
	 */
	public static void main(String[] args)
	{
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Douban.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Douban.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Douban.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Douban.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        Douban mainwindow = new Douban();
        Thread thread = new Thread(mainwindow);
        thread.start();
        
	}

}
