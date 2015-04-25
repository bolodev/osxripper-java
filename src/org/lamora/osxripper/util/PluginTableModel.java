package org.bolodev.osxripper.util;

import javax.swing.table.DefaultTableModel;

/**
 * Extension of DefaultTableModel, sets columns zero to read only
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class PluginTableModel extends DefaultTableModel {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = -925930255326921714L;

	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int row, int col){
		if (col == 0){
			return false;
		}
		else{
			return true;
		}
	}
	
}
