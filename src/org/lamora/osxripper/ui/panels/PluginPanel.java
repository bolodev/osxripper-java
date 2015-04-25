package org.bolodev.osxripper.ui.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

import org.bolodev.osxripper.core.OSXRipperModel;
import org.bolodev.osxripper.mvc.Controller;
import org.bolodev.osxripper.util.PluginTableModel;

/**
 * Panel displaying plugin table, implements local listener interfaces to handle user actions
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class PluginPanel extends JPanel implements ActionListener, TableModelListener{

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = 3023874789671973687L;
	private JCheckBox chkAll;
	private JScrollPane pnScroll;
	private JTable pluginTable;
	private String colNames[] = new String[]{"Plugin", ""};
	private PluginTableModel dtm;
	private Controller theController;
	
	/**
	 * Constructor
	 * @param aController controller passed to the listener
	 * @param aPluginList list of plugin names to display
	 */
	public PluginPanel(Controller aController, Object[] aPluginList){
		theController = aController;
		initialise(theController, aPluginList);
	}
	
	/**
	 * Initialise the components
	 * @param aController controller passed to the listener
	 * @param aPluginList list of plugin names to display
	 */
	private void initialise(Controller aController, Object[] aPluginList){
		setLayout(new BorderLayout());
		setBorder(new EtchedBorder());
		
		chkAll = new JCheckBox("Select All");
		chkAll.setSelected(true);
		chkAll.addActionListener(this);
		
		dtm = new PluginTableModel();
		dtm.setColumnIdentifiers(colNames);
		dtm.addTableModelListener(this);
		pluginTable = new JTable(dtm);
		pluginTable.getColumnModel().getColumn(1).setPreferredWidth(20);
		pluginTable.getColumnModel().getColumn(1).setMaxWidth(20);
		TableColumn tc = pluginTable.getColumnModel().getColumn(1);
        tc.setCellEditor(pluginTable.getDefaultEditor(Boolean.class));
        tc.setCellRenderer(pluginTable.getDefaultRenderer(Boolean.class));
		for(int i = 0 ; i < aPluginList.length ; i++){
			dtm.addRow(new Object[]{aPluginList[i], new Boolean(true)});
		}
		
		pnScroll = new JScrollPane(pluginTable);
		add(chkAll, BorderLayout.NORTH);
		add(pnScroll, BorderLayout.CENTER);
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Select All")){
			int selectedtabIndex = ((JTabbedPane)this.getParent()).getSelectedIndex();
			String tabTitle = ((JTabbedPane)this.getParent()).getTitleAt(selectedtabIndex);
			int plugins = dtm.getRowCount();
			for(int i = 0 ; i < plugins ; i++){
				dtm.setValueAt(chkAll.isSelected(), i, 1);
				((OSXRipperModel)theController.getModel()).setPluginsActive(tabTitle, chkAll.isSelected());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.event.TableModelListener#tableChanged(javax.swing.event.TableModelEvent)
	 */
	@Override
	public void tableChanged(TableModelEvent arg0) {
		if(arg0.getColumn() != -1){
			int selectedtabIndex = ((JTabbedPane)this.getParent()).getSelectedIndex();
			String tabTitle = ((JTabbedPane)this.getParent()).getTitleAt(selectedtabIndex);
			((OSXRipperModel)theController.getModel()).setPluginsActive(tabTitle, (String) dtm.getValueAt(arg0.getFirstRow(), 0), (Boolean) dtm.getValueAt(arg0.getFirstRow(), arg0.getColumn()));
		}
		
		
	}

}
