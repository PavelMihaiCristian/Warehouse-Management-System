package ServerView;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;

import javax.swing.JScrollBar;

import java.awt.Dimension;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Scrollbar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class ServerGUI {

	private JFrame frmServer;
	TextArea textArea = new TextArea();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerGUI window = new ServerGUI();
					//window.frmServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ServerGUI() {
			initialize();
			frmServer.setVisible(true);	
			frmServer.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmServer = new JFrame();
		frmServer.setTitle("Server");
		frmServer.setBounds(100, 100, 450, 300);
		frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Panel panel = new Panel();
		frmServer.getContentPane().add(panel, BorderLayout.CENTER);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 434, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 261, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		
		textArea.setEditable(false);
		frmServer.getContentPane().add(textArea, BorderLayout.CENTER);		
	}

	public void showMessage(String text)
	{
		textArea.append(text+"\n");
	}
	
	public void showErrorMessage()
	{
		JOptionPane.showMessageDialog(null, "Problems starting the server, please try again...");
	}
}
