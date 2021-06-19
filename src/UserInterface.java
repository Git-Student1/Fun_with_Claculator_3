
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

// Methoden die CalcEngine erfordern sind noch ausgeklammert/ graut. Parameter für calc methoden sind 
// aktuell string, methoden zum umwandeln befinden sich am ende.
public class UserInterface implements ActionListener {

	private JFrame frame;
	private JTextField display1;
	private JTextField display2;
	private JTextField display3;
	
	private String s1;
	private String s2;
	
	private CalcEngine calc;
	
	public static void main (String [] args) {
   	 new UserInterface(new CalcEngine());
   }
	
	public UserInterface(CalcEngine engine) {
			calc = engine;
		 makeFrame();
		 frame.setVisible(true);
	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

	private void makeFrame() {
		frame = new JFrame();

		JPanel contentPane = (JPanel) frame.getContentPane();
		contentPane.setLayout(new BorderLayout(8, 8));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

		JPanel textPanel = new JPanel(new GridLayout(2, 1));
		display1 = new JTextField();
		textPanel.add(display1);
		display2 = new JTextField();
		textPanel.add(display2);
		contentPane.add(textPanel, BorderLayout.NORTH);
		
		JPanel buttonPanel = getButtonPenel();
		contentPane.add(buttonPanel, BorderLayout.CENTER);
		
		display3 = new JTextField();
		contentPane.add(display3, BorderLayout.SOUTH);
		
		frame.pack();
	}
	
	protected JPanel getButtonPenel() {
		JPanel buttonPanel = new JPanel(new GridLayout(2, 4));
		
		addButton(buttonPanel, "Union");
		addButton(buttonPanel, "Intersection");
		addButton(buttonPanel, "Subtraction");
		addButton(buttonPanel, "Clear");
		addButton(buttonPanel, "Push Set A");
		addButton(buttonPanel, "Push Set B");
		addButton(buttonPanel, "Length Set A");
		addButton(buttonPanel, "Length Set B");
		
		return buttonPanel;
		}
	
	private void addButton(Container panel, String buttonText)
    {
        JButton button = new JButton(buttonText);
        button.addActionListener(this);
        panel.add(button);
    }

	
	public void actionPerformed(ActionEvent event)
    {
        String command = event.getActionCommand();
        s1 = display1.getText();
		s2 = display2.getText();
        calc.getSets(s1,s2);
        if (command.equals("Union")) {
			calc.union();
		} else if (command.equals("Intersection")) {
			calc.intersection();
		} else if (command.equals("Subtraction")) {
			calc.subtraction();
		} else if (command.equals("Clear")) {
			display1.setText("( ͡o ͜ʖ ͡o)");
			display2.setText("( ͡o ͜ʖ ͡o)");
			display3.setText("( ͡o ͜ʖ ͡o)");
		} else if (command.equals("Push Set A")) {
			display1.setText(display3.getText());
		} else if (command.equals("Push Set B")) {
			display2.setText(display3.getText());
		} else if (command.equals("Length Set A")) {
			calc.lengthSetA();
		} else if (command.equals("Length Set B")) {
			//calc.lengthSetA()
			calc.lengthSetB();
		}
        
        redisplay();
    }
	
	// Pls create a new method called getDisplay() that returns the output String.
	void redisplay()
    {
        display3.setText("" + calc.getDisplayString());
    }
	
	// Am besten hier ausschneiden und in CalcEngine einfügen oder bei jedem erforderlichen command in actionPerformed einfügen.

	
	// Auch für CalcEngine. Call diese methode am ende der berechnungen um das set zurück zu einem String zu casten.
	
}
