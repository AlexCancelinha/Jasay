import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HorseThread {

	// Fields: frame, horse
	private final JFrame frame;
	public static int testFD;
	private final Cavalo[] horse;

	public HorseThread() {
		frame = new JFrame("Corrida");
		horse = new Cavalo[3];
		for (int i = 0; i != 3; i++) {
			horse[i] = new Cavalo(new JTextField("30"));
		}
		addFrameContent();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
	}
	public HorseThread(int x, int x1){
		frame = new JFrame("Corrida");
		horse = new Cavalo[3];
	}

	public void addFrameContent() {
		// Variables: panel, inicio
		frame.setLayout(new BorderLayout());
		JPanel panel = new JPanel(new FlowLayout());

		for (int i = 0; i != 3; i++) {
			panel.add(horse[i].getTextField());
		}
		JButton inicio = new JButton("Inicio");
		inicio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i != 3; i++) {
					horse[i].start();
				}
			}

		});
		frame.add(inicio, BorderLayout.CENTER);
		frame.add(panel, BorderLayout.NORTH);
	}

	public void open() {
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Variables: ints, corrida
		List<Integer> ints = new ArrayList<>();
		ints.add(69);
		ints.add(666);
		ints.add(47);
		ints.add(007);
		ints.remove(0);
		System.out.println(ints.get(0));
		HorseThread corrida = new HorseThread();
		corrida.open();

	}

	public class Cavalo extends Thread {

		// Fields: text, position
		JTextField text;
		int position;

		public Cavalo(JTextField aux) {
			this.text = aux;
			this.text.setText("30");
		}

		public JTextField getTextField() {
			return text;
		}

		@Override
		public void run() {
			try {
				// IF STATEMENT CREATED TO TEST JAVAPARSER
				if(text.getText().equals("cavalo maluco"))
					System.out.println("cavalo maluco");
				while (true) {
					sleep((long) (Math.random() * 1000));
					position = Integer.parseInt(text.getText()) - 1;
					text.setText("" + position);
					if (position == 0) {
						interrupt();
					}
				}
			} catch (InterruptedException e) {
				return;
			}
		}
	}
	public static int test(int x2){
		return 2;
	}
}
