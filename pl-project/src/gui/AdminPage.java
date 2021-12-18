package gui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.xml.transform.TransformerException;

import gui.TestJButton.ButtonEditor;
import gui.TestJButton.ButtonRenderer;

import javax.swing.JTextField;

public class AdminPage extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	JButton moifyButton = new JButton();
	JButton deleteButton = new JButton();

	private String[] columns = new String[5];
	private String[][] data;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage frame = new AdminPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminPage() { // constructor
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("project");
		btnNewButton.setBounds(10, 81, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Users");
		btnNewButton_1.setBounds(10, 146, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JPanel usersPanel = new JPanel();
		usersPanel.setBounds(112, 20, 433, 209);
		contentPane.add(usersPanel);
		usersPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 433, 233);
		usersPanel.add(scrollPane);
		textField = new JTextField();
		textField.setBounds(10, 45, 96, 19);
//		contentPane.add(textField);
		textField.setColumns(10);
		columns = new String[] { "Id", "User Name", "Password", "Edit", "Delete"};
//		data = new String[][] { { "1", "Thomas","asd" }, { "2", "Jean", "sad" }, { "3", "Yohan","asd" } };
		data = XMLOperation.getUsersData();

		DefaultTableModel model = new DefaultTableModel(data, columns);
		
		table = new JTable() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return column != 0;
		    }
		};
		table.setModel(model);
		table.getColumn("Edit").setCellRenderer(new ButtonRenderer());
		table.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox()));
		table.getColumn("Delete").setCellRenderer(new ButtonDeleteRenderer());
		table.getColumn("Delete").setCellEditor(new ButtonDelete(new JCheckBox()));
		
		moifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			table.getValueAt(1,1);
			if(JOptionPane.showConfirmDialog(null, "Do you want to modify this line?") == 0) {
				int row = table.getSelectedRow();
				String id = table.getModel().getValueAt(row, 0).toString();
				String userName = table.getModel().getValueAt(row, 1).toString();
				String password = table.getModel().getValueAt(row, 2).toString();
				
				try {
					XMLOperation.updateUser(id, userName, password);
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
//				System.out.println("SelectedRow" + row);
//				System.out.println("New ID "+  table.getModel().getValueAt(row, 0).toString());
//				System.out.println("New UserName "+  table.getModel().getValueAt(row, 1).toString());
//				System.out.println("New Password "+  table.getModel().getValueAt(row, 2).toString());


			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(276, 242, 85, 21);
		contentPane.add(btnNewButton_2);
		
	}
	class ButtonRenderer extends JButton implements TableCellRenderer {
		public ButtonRenderer() {
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			setText((value == null) ? "Modify" : value.toString());
			return this;
		}
	}
	
	class ButtonDeleteRenderer extends JButton implements TableCellRenderer {
		public ButtonDeleteRenderer() {
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			setText((value == null) ? "Delete" : value.toString());
			return this;
		}
	}

	class ButtonEditor extends DefaultCellEditor {
		private String label;

		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			label = (value == null) ? "Modify" : value.toString();
			moifyButton.setText(label);
			return moifyButton;
		}

		public Object getCellEditorValue() {
			return new String(label);
		}
	}
	
	class ButtonDelete extends DefaultCellEditor {
		private String label;

		public ButtonDelete(JCheckBox checkBox) {
			super(checkBox);
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			label = (value == null) ? "Delete" : value.toString();
			deleteButton.setText(label);
			return deleteButton;
		}

		public Object getCellEditorValue() {
			return new String(label);
		}
	}
}



