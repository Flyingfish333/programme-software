
import java.awt.event.ActionListener;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class Components {

	public Components() {}

	public JComboBox makeComboBox(JPanel p, String[] list, String label, ActionListener e) {
		JComboBox b = new JComboBox(list);
		if (label != "") {
			JLabel l = new JLabel(label);
			p.add(l);
		}
		b.addActionListener(e);
		p.add(b);
		return b;
	}
	
	public JButton makeJButton(JPanel p, String l, ActionListener e) {
		JButton b = new JButton(l);
		b.addActionListener(e);
		p.add(b);
		return b;
	}

    public JRadioButton makeJRadioButton(JPanel p, String l, ActionListener e) {
		JRadioButton b = new JRadioButton(l);
		b.addActionListener(e);
		p.add(b);
		return b;
	}
		
	public String[] DateList(int start, int end) {
		String[] result = IntStream.rangeClosed(start, end).mapToObj(Integer::toString).toArray(String[]::new);
		return result;
	}

}