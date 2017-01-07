package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class SimpleTablePanel extends JPanel implements TableCellRenderer {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane scroll;
	private String[][] data;
	private String[] columnNames;
	private int[] columnWidths;
	private int width = 340;
	private int height = 485;


	// constructor 1
	public SimpleTablePanel() {

		// some random values for testing
		this(new String[][] { { "1", "2", "3", "4" }, { "a", "b", "c", "d" }, { "w", "x", "y", "z" } },
				new String[] { "col1", "col2", "col3", "col4" });
	}

	// constructor 2
	public SimpleTablePanel(String[][] data, String[] columnNames) {

		this(data, columnNames, null);
	}

	// constructor 3
	public SimpleTablePanel(String[][] data, String[] columnNames, int[] columnWidths) {

		this.columnWidths = columnWidths;
		this.columnNames = columnNames;
		this.data = data;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		table = new JTable(data, columnNames);
		formatTable();

		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(width, height));
		add(scroll);
		

	} // end constructor
	
	private void formatTable(){
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 17));
		table.setRowHeight(table.getRowHeight() + 10);

		table.setFont(new Font("Courier", Font.PLAIN, 17));

		if (columnWidths != null) {
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			for (int i = 0; i < columnWidths.length; i++) {
				table.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
			}
		}
		// render the table
		table.setDefaultRenderer(Object.class, this); // use overridden method
														// below
		table.setDefaultEditor(Object.class, null);
	}
	


	// TableCellRenderer method to create zebra style table format
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Color NEARLY_WHITE = Color.decode("#F9F9F9");
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();

		JLabel label = (JLabel) renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		// Apply zebra style on table rows
		label.setBackground(row % 2 == 0 ? Color.WHITE : NEARLY_WHITE);
		return label;
	}
	
	
	
	public void updateTable (String[][] newData){
		
		this.data = newData;
		
		table = new JTable(data, columnNames);
		formatTable();
	
		scroll.setVisible(false);
		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(width, height));
		scroll.setVisible(true);
		add(scroll);
		
	}


	public void setPaneWidth(int width) {
		this.width = width;
		scroll.setPreferredSize(new Dimension(width, height));
	}

}
