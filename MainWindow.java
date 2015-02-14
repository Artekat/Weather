import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class MainWindow extends javax.swing.JFrame {
	String[] dane = new String[5];
	int[] temph = new int[5];
	int[] templ = new int[5];
	public JFrame frmDfs;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();

					window.frmDfs.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindow() {
		initialize();
	}

	public void OknoGlowne() {

		DownloadsData Dane = null;
		try {
			Dane = new DownloadsData();
		} catch (UnsupportedEncodingException e) {
			JOptionPane
					.showMessageDialog(
							frmDfs,
							"Niepoprawne dane. Proszê skontaktowaæ siê z twórc¹ aplikacji.\ne-mail: pawel.klos@hotmail.com");
			System.exit(0);
		} catch (IOException e) {
			JOptionPane
					.showMessageDialog(
							frmDfs,
							"Niepoprawne dane. Proszê skontaktowaæ siê z twórc¹ aplikacji.\ne-mail: pawel.klos@hotmail.com");
			System.exit(0);
		}

		for (int i = 0; i < 5; i++) {
			this.dane[i] = Dane.date[i];
			this.temph[i] = Dane.high[i];
			this.templ[i] = Dane.low[i];
		}
		mainWindowSettings();
		buttonExit();
		labelDay(Dane);
		labelTemperatureMaxMin(Dane);
		labelRiseSetSun(Dane);
		buttonAuthor();
		buttonInterpolationMaxTemperatures();
		buttonInterpolationMinTemperatures();
	}

	public void labelDay(DownloadsData Dane) {
		JLabel lblNewLabel = new JLabel("Dzi\u015B jest " + Dane.date[0]
				+ " roku");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 12, 579, 50);
		frmDfs.getContentPane().add(lblNewLabel);
	}

	public void buttonAuthor() {
		JButton btnNewButton = new JButton("O autorze");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AboutTheAuthor autor = new AboutTheAuthor();
				autor.main();
			}
		});
		btnNewButton.setBounds(158, 298, 289, 48);
		frmDfs.getContentPane().add(btnNewButton);
	}

	public void buttonExit() {
		JButton btnOpady = new JButton("Wyj\u015Bcie");
		btnOpady.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});

		btnOpady.setBounds(158, 358, 289, 48);
		frmDfs.getContentPane().add(btnOpady);
	}

	public void labelRiseSetSun(DownloadsData Dane) {
		if ((Dane.sunAmPmTime[0].equals("pm")) && (Dane.sunRiseSet[0] != 12)) {
			Dane.sunRiseSet[0] += 12;
		}
		String gdyZero = "0";
		if (Dane.sunRiseSet[1] == 0) {
			gdyZero = "00";
		} else {
			gdyZero = Integer.toString(Dane.sunRiseSet[1]);
		}
		JLabel lblNewLabel_3 = new JLabel("S³oñce wschodzi o: "
				+ DownloadsData.sunRiseSet[0] + ":" + gdyZero);
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_3.setBounds(90, 116, 200, 50);
		frmDfs.getContentPane().add(lblNewLabel_3);

		if ((Dane.sunAmPmTime[1].equals("pm")) && (Dane.sunRiseSet[2] != 12)) {
			Dane.sunRiseSet[2] += 12;
		}

		gdyZero = "0";
		if (Dane.sunRiseSet[3] == 0) {
			gdyZero = "00";
		} else {
			gdyZero = Integer.toString(Dane.sunRiseSet[3]);

		}
		JLabel lblNewLabel_4 = new JLabel("S³oñce zachodzi o: "
				+ Dane.sunRiseSet[2] + ":" + gdyZero);
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_4.setBounds(357, 115, 200, 50);
		frmDfs.getContentPane().add(lblNewLabel_4);

	}

	public void buttonInterpolationMinTemperatures() {
		JButton btnNewButton_1 = new JButton(
				"Interpolation minimalnych temperatur");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PaintDemo InterpolacjaMax = new PaintDemo(dane, templ);
			}
		});
		btnNewButton_1.setBounds(158, 238, 289, 48);
		frmDfs.getContentPane().add(btnNewButton_1);
	}

	public void buttonInterpolationMaxTemperatures() {
		JButton btnCinienie = new JButton(
				"Interpolation maksymalnych temperatur");
		btnCinienie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				PaintDemo InterpolacjaMax = new PaintDemo(dane, temph);

			}
		});
		btnCinienie.setBounds(158, 178, 289, 48);
		frmDfs.getContentPane().add(btnCinienie);
	}

	public void labelTemperatureMaxMin(DownloadsData Dane) {
		JLabel lblNewLabel_1 = new JLabel("Maksymalna temperatura: "
				+ Dane.high[0]);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_1.setBounds(12, 52, 220, 57);
		frmDfs.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Minimalna temperatura: "
				+ Dane.low[0]);
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_2.setBounds(376, 52, 215, 57);
		frmDfs.getContentPane().add(lblNewLabel_2);
	}

	public void mainWindowSettings() {
		frmDfs.setTitle("Prognoza pogody");
		frmDfs.setResizable(false);
		frmDfs.setBounds(100, 100, 609, 447);
		frmDfs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDfs.getContentPane().setLayout(null);
	}

	private void initialize() {

		frmDfs = new JFrame();

		OknoGlowne();
	}
}
