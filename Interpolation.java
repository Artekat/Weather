import java.awt.Color;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

public class Interpolation extends JPanel {

	int temperature[] = new int[] { -100, -100, -100, -100, -100 };
	String[] data = new String[] { "brak", "brak", "brak", "brak", "brak" };
	int dynamicSizeWindow;
	int level0;
	double sliderValue;
	int maxTemperature;
	String textValue = " ";
	JFrame jfrm;
	boolean yes = true;

	public Interpolation(String[] data, int temperature[], JFrame jfrm) {
		System.out.println("Interpolation konstruktor");
		this.jfrm = jfrm;

		for (int i = 0; i < 5; i++) {

			this.temperature[i] = temperature[i];
		}
		this.data = data;

		int max = temperature[0];
		int min = temperature[0];
		for (int a : temperature) {
			if (max < a)
				max = a;
			if (min > a)
				min = a;
		}
		this.maxTemperature = max;
		max = min;
		if (max * 10 + level0 >= 100)
			for (; max * 10 + level0 >= 100; level0--) {
			}
		else
			for (; max * 10 + level0 <= 100; level0++) {
			}
		dynamicSizeWindow = (max * 10 + level0) + (min * 10 + level0) + 100;
		if (dynamicSizeWindow < 0)
			dynamicSizeWindow = dynamicSizeWindow * dynamicSizeWindow;

	}

	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		int x = 0, y = 0, x2 = 0, y2 = 0;
		drawingGraph(g, x, y, x2, y2);
		temperaturePrint();
		sliderTemperature();
		displayOfTheCurrentLine(g);
	}

	public void sliderTemperature() {

		final JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 400, 100);
		slider.setBounds(100, dynamicSizeWindow - 40, 400, 26);
		slider.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent arg0) {

				sliderValue = slider.getValue();
				jfrm.validate();
				jfrm.repaint();

			}

		});
		slider.setValue((int) sliderValue);
		jfrm.add(slider);
	}

	public void displayOfTheCurrentLine(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawLine(100 + (int) sliderValue, temperature[0] * 10 + level0 - 10,
				1 * +100 + (int) sliderValue, maxTemperature * 10 + level0 + 10);

	}

	public void temperatureCalculation() {
		if (sliderValue * 0.01 <= 1 && sliderValue * 0.01 >= 0) {
			textValue = Double
					.toString(rys(0, temperature, sliderValue * 0.01));

		} else if (sliderValue * 0.01 <= 2 && sliderValue * 0.01 >= 1) {
			textValue = Double
					.toString(rys(1, temperature, sliderValue * 0.01));

		} else if (sliderValue * 0.01 <= 3 && sliderValue * 0.01 >= 2) {
			textValue = Double
					.toString(rys(2, temperature, sliderValue * 0.01));
		} else if (sliderValue * 0.01 <= 4 && sliderValue * 0.01 >= 3) {
			textValue = Double
					.toString(rys(3, temperature, sliderValue * 0.01));
		}

	}

	public void temperaturePrint() {
		temperatureCalculation();
		String s = textValue;
		double val = Double.parseDouble(s);
		val *= 100;
		val = Math.round(val);
		val /= 100;

		JTextPane txtpnTekst = new JTextPane();
		txtpnTekst.setBackground(SystemColor.control);
		txtpnTekst.setEditable(false);
		if (val > 0)
			txtpnTekst.setForeground(new Color(255, 0, 0));
		else if (val < 0)
			txtpnTekst.setForeground(SystemColor.textHighlight);
		else
			txtpnTekst.setForeground(new Color(0, 0, 0));

		txtpnTekst.setText(Double.toString(val) + "°C");
		txtpnTekst.setBounds(300, dynamicSizeWindow - 60, 50, 20);
		jfrm.getContentPane().add(txtpnTekst);
	}

	public void drawingGraph(Graphics g, int x, int y, int x2, int y3) {
		for (int i = 0; i < 4; i++) {
			x = i + 1;
			y = temperature[i];
			x2 = (int) (((i + 1.5) * 100));
			y3 = temperature[i + 1];
			g.drawLine(x * 100, y * 10 + level0, (x + 1) * 100, y3 * 10
					+ level0);
			g.setColor(Color.RED);
			g.drawLine(x * 100, y * 10 + level0, x * 100, maxTemperature * 10
					+ level0 + 10);
			g.setColor(Color.BLACK);

		}
		g.setColor(Color.RED);
		g.drawLine(5 * 100, temperature[4] * 10 + level0, 5 * 100,
				maxTemperature * 10 + level0 + 10);
	}

	public double rys(int i, int tab[], double sliderValue) {
		double k = (tab[i] + (tab[i + 1] - tab[i]) * ((sliderValue) - i)
				/ ((i + 1) - (i)));
		k = k * -1;
		return k;
	}

	public void labelComponent(Graphics g) {
		JLabel label = new JLabel("");
		label.setText("2 czerwiec 2004");
		label.setBounds(100 - 40, 35 * 10 - 101, 200, 50);
	}
}

class PaintDemo {
	JLabel jlab;
	Interpolation pp;

	PaintDemo(final String[] data, final int[] temperature) {

		final JFrame jfrm = new JFrame("Interpolation");
		jfrm.getContentPane().setLayout(null);
		pp = new Interpolation(data, temperature, jfrm);
		dateDays(jfrm);
		temperatureDays(jfrm);
		pp.setBounds(1, 1, 700, pp.dynamicSizeWindow - 100);
		jfrm.add(pp);
		jfrm.setBounds(1, 1, 700, pp.dynamicSizeWindow + 30);
		jfrm.setVisible(true);

	}

	public void temperatureDays(JFrame jfrm) {
		JLabel temperature0 = new JLabel("");
		JLabel temperature1 = new JLabel("");
		JLabel temperature2 = new JLabel("");
		JLabel temperature3 = new JLabel("");
		JLabel temperature4 = new JLabel("");

		temperature0.setText(pp.temperature[0] * -1 + "°C");
		temperature0.setBounds(100 - 7,
				pp.temperature[0] * 10 + pp.level0 - 20, 30, 20);
		temperature1.setText(pp.temperature[1] * -1 + "°C");
		temperature1.setBounds(200 - 7,
				pp.temperature[1] * 10 + pp.level0 - 20, 30, 20);
		temperature2.setText(pp.temperature[2] * -1 + "°C");
		temperature2.setBounds(300 - 7,
				pp.temperature[2] * 10 + pp.level0 - 20, 30, 20);
		temperature3.setText(pp.temperature[3] * -1 + "°C");
		temperature3.setBounds(400 - 7,
				pp.temperature[3] * 10 + pp.level0 - 20, 30, 20);
		temperature4.setText(pp.temperature[4] * -1 + "°C");
		temperature4.setBounds(500 - 7,
				pp.temperature[4] * 10 + pp.level0 - 20, 30, 20);

		jfrm.getContentPane().add(temperature0);
		jfrm.getContentPane().add(temperature1);
		jfrm.getContentPane().add(temperature2);
		jfrm.getContentPane().add(temperature3);
		jfrm.getContentPane().add(temperature4);
	}

	public void dateDays(JFrame jfrm) {

		JLabel data0 = new JLabel("");
		JLabel data1 = new JLabel("");
		JLabel data2 = new JLabel("");
		JLabel data3 = new JLabel("");
		JLabel data4 = new JLabel("");

		data0.setText(pp.data[0]);
		data0.setBounds(100 - 40, pp.maxTemperature * 10 + pp.level0, 200, 50);
		data1.setText(pp.data[1]);
		data1.setBounds(200 - 40, pp.maxTemperature * 10 + pp.level0, 200, 50);
		data2.setText(pp.data[2]);
		data2.setBounds(300 - 40, pp.maxTemperature * 10 + pp.level0, 200, 50);
		data3.setText(pp.data[3]);
		data3.setBounds(400 - 40, pp.maxTemperature * 10 + pp.level0, 200, 50);
		data4.setText(pp.data[4]);
		data4.setBounds(500 - 40, pp.maxTemperature * 10 + pp.level0, 200, 50);

		jfrm.getContentPane().add(data0);
		jfrm.getContentPane().add(data1);
		jfrm.getContentPane().add(data2);
		jfrm.getContentPane().add(data3);
		jfrm.getContentPane().add(data4);
	}

	public static void main() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				int temperature[] = new int[] { -100, -100, -100, -100, -100 };
				String[] data = new String[] { "brak", "brak", "brak", "brak",
						"brak" };
				new PaintDemo(data, temperature);
			}
		});
	}
}