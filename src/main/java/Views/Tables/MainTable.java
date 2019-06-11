package Views.Tables;

import java.awt.*;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

import javax.swing.table.TableModel;
import DB.JavaDB;
import Views.VehiclePropertiesWindow;

import javax.swing.table.DefaultTableModel;

import javax.swing.table.*;

public class MainTable extends JFrame

{
	private JTable table;

	private String[] columnNames = new String[5];

	JButton button = new JButton();

	public MainTable(JPanel topPanel, JScrollPane scrollPane)
	{
		columnNames = new String[] { "Id","Typ pojazdu", "Marka pojazdu", "Model", "Numer rejestracyjny", "Wlasciwosci" };
		TableModel model = new DefaultTableModel(null, columnNames);
		table = new JTable();
		table.setModel(model);
		table.getColumnModel().getColumn(1).setWidth(10);
		table.getColumn("Wlasciwosci").setCellRenderer(new ButtonRenderer());
		table.getColumn("Wlasciwosci").setCellEditor(new ButtonEditor(new JCheckBox()));

		scrollPane.setViewportView(table);
		button.setText("Otw�rz");
		/**
		 * Przycisk wywo�uj�cy otwarcie okna z w�a�ciwo�ciami pojazdu
		 */
		button.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						
						int row = table.getSelectedRow();
						Object id = table.getValueAt(row, 0);
						VehiclePropertiesWindow propertiesWindow = new VehiclePropertiesWindow((int) id);
						propertiesWindow.show();
						
					}
				}
		);
		/**
		 * Wywo�anie funkcji pobieraj�cej dane z bazy danych do tabeli
		 */
		addRowToTable();
	}
	
	/**
	 * Metoda odpowiadaj�ca za pobieranie pojazd�w z bazy danych i dodawanie ich w formie wierszy tabeli
	 */
	public void addRowToTable() {
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		try {
            Connection connection = JavaDB.connectToDB();
            Statement stat = connection.createStatement();
            /**
             *  Polecenie wyszukania
             */
            String searchSQL = "SELECT Id, vehicleType, mark, model, registrationNumber FROM Vehicle;";
            ResultSet result = stat.executeQuery(searchSQL);
            System.out.println("wynik polecenia:\n" + searchSQL);

            while (result.next()) {
                model.addRow(new Object[] {result.getInt("Id"), result.getString("vehicleType"), result.getString("mark"), result.getString("model"),
                		result.getString("registrationNumber"),""
                });
             }
            result.close();
            stat.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Nie mog� wyszuka� danych " + e.getMessage());
        }
	}
	/**
	 * Klasa odpowiadaj�ca za generowanie przycisku
	 */
	class ButtonRenderer extends JButton implements TableCellRenderer {
		public ButtonRenderer() {
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			setText("Otw�rz");
			return this;
		}
	}
	/**
	 * Klasa odpowiadaj�ca za w�a�ciwo�ci przycisku
	 */
	class ButtonEditor extends DefaultCellEditor {
		private String label;
		
		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			return button;
		}
		
		public Object getCellEditorValue() {
			return ""; // new String(label);
		}
	}
}
