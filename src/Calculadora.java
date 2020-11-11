import java.awt.*;
import java.awt.event.*;

public class Calculadora extends Frame {

	private int total = 0;
	private boolean suma = false;
	private boolean resta = false;

    public static void main(String[] args) {
    	Calculadora ventanaPrincipal = new Calculadora();
    	ventanaPrincipal.setVisible(true);
    	ventanaPrincipal.setSize(250, 300);
        ventanaPrincipal.setTitle("Calculadora");

        ventanaPrincipal.addWindowListener(
        	new WindowAdapter() {
        		public void windowClosing(WindowEvent e) {
        			System.exit(0);
        		}
        	}
        	);
    }

    Calculadora() { 
    	Panel panelPrincipal = new Panel(new BorderLayout());
    	this.add(panelPrincipal);

    	Panel panelTexto = new Panel();
    	panelPrincipal.add(panelTexto, BorderLayout.NORTH);

    	Panel panelNumeros = new Panel(new GridLayout(4,4));
    	panelPrincipal.add(panelNumeros, BorderLayout.CENTER);

    	TextField campoTexto = new TextField("0", 16);
		Font bigFont = new Font("Comic Sans MS",0,23);
    	campoTexto.setFont(bigFont);
    	panelTexto.add(campoTexto);

    	Button botones[] = new Button[] {
    			new Button("1"),
				new Button("2"),
				new Button("3"),
				new Button("+"),
				new Button("4"),
				new Button("5"),
				new Button("6"),
				new Button("-"),
				new Button("7"),
				new Button("8"),
				new Button("9"),
				new Button("*"),
				new Button(","),
				new Button("0"),
				new Button("C"),
				new Button("="),
		};

    	for (Button tecla : botones) {
    		tecla.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					if (campoTexto.getText().length() > 0 && tecla.getLabel().equals("C")) {
						campoTexto.setText(campoTexto.getText().substring(0, campoTexto.getText().length() - 1));
					}
					else if (tecla.getLabel().equals(",")) {
						if (!campoTexto.getText().contains(",")) {
							campoTexto.setText(campoTexto.getText() + tecla.getLabel());
						}
					}
					else {
						if (tecla.getLabel().equals("+")) {
							total += Integer.parseInt(campoTexto.getText());
							campoTexto.setText("");
							suma = true;
							resta = false;

						} else if (tecla.getLabel().equals("-")) {
							if (resta) {
								total -= Integer.parseInt(campoTexto.getText());
								campoTexto.setText("");
								resta = true;
								suma = false;
							} else {
								total = Integer.parseInt(campoTexto.getText());
								campoTexto.setText("");
								resta = true;
							}
						} else if (tecla.getLabel().equals("=")) {
							if (!campoTexto.getText().equals("")) {
								if (suma) {
									total += Integer.parseInt(campoTexto.getText());
									suma = false;
								} else if (resta) {
									total -= Integer.parseInt(campoTexto.getText());
									resta = false;
								}
							}
							campoTexto.setText(total + "");
						} else {
							if (campoTexto.getText().equals("0")) {
								campoTexto.setText("");
							}
							campoTexto.setText(campoTexto.getText() + tecla.getLabel());
						}
					}
				}
			});
			panelNumeros.add(tecla);
		}
	}
}