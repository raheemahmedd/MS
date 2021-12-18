package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JTextField passwordTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage(); // an obj from loginPage class
					frame.setVisible(true);  // frame appears 
				} catch (Exception e) {
					e.printStackTrace(); 
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginPage() {  // constructor of loginPage...
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel loginLabel = new JLabel("Login Page");
		loginLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		loginLabel.setBounds(166, 22, 85, 21);
		contentPane.add(loginLabel);
		
		JLabel userNameLabel = new JLabel("UserName");
		userNameLabel.setBounds(63, 79, 74, 13);
		contentPane.add(userNameLabel);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(147, 76, 136, 19);
		contentPane.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(63, 111, 59, 13);
		contentPane.add(passwordLabel);
		
		passwordTextField = new JTextField();
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(147, 108, 136, 19);
		contentPane.add(passwordTextField);
		
		JButton loginbtn = new JButton("Login");
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameTextField.getText();
				String password = passwordTextField.getText();
				
				System.out.println("username = " + username);
				System.out.println("password = " + password);  
				String type = new XMLOperation().validateUser(username, password);
				if(type == null) {  
					//pring error message
					System.out.println("incorrect user name or password");

				}
				else {
					System.out.println("type = " + type);
                      dispose();
                      AdminPage adminPage = new AdminPage();
                      adminPage.setVisible(true);
                      
					//close login page
//					switch (type) {
//					case "admin": {
//						
//					}
//					default:
//						throw new IllegalArgumentException("Unexpected value: " + type);
//					}
				}
			}

			
		});
		loginbtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		loginbtn.setBounds(166, 190, 85, 21);
		contentPane.add(loginbtn);
		
		
	}

}
