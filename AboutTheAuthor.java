import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class AboutTheAuthor {

	public static JFrame frame;

	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutTheAuthor window = new AboutTheAuthor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AboutTheAuthor() {
		initialize();
	}

	public void initialize() {
		mainWindowSettings();
		imageIcon();
		buttonOk();
		labelAll();
	}

	public static void imageIcon() {
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new javax.swing.ImageIcon(frame.getClass().getResource(
				"/ZasobyGraficzne/Dev1b.male.png"))); // wybieranie obrazka z
														// zasobów
		lblNewLabel.setBounds(10, 11, 620, 435);
		frame.getContentPane().add(lblNewLabel);
	}

	public static void labelAll() {
		JLabel lblDzikujeZaSkorzystanie = new JLabel(
				"Dzi\u0119kuje za skorzystanie z mojej aplikacji.");
		lblDzikujeZaSkorzystanie.setBounds(10, 468, 410, 14);
		frame.getContentPane().add(lblDzikujeZaSkorzystanie);

		JLabel lblZapraszamNaStron = new JLabel(
				"Zapraszam na stron\u0119 internetow\u0105:");
		lblZapraszamNaStron.setBounds(10, 493, 314, 14);
		frame.getContentPane().add(lblZapraszamNaStron);

		JLabel lblNewLabel_1 = new JLabel(
				"http://www.iis.pwsz.elblag.pl/~16582/klos/");
		lblNewLabel_1.setBounds(10, 519, 314, 14);
		frame.getContentPane().add(lblNewLabel_1);
	}

	public static void buttonOk() {
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setVisible(false);
			}
		});
		btnOk.setBounds(404, 457, 226, 76);
		frame.getContentPane().add(btnOk);
	}

	public static void mainWindowSettings() {
		frame = new JFrame();
		frame.setBounds(100, 100, 657, 583);
		frame.getContentPane().setLayout(null);

	}
}
