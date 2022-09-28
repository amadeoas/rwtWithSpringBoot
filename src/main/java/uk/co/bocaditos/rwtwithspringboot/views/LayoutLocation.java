package uk.co.bocaditos.rwtwithspringboot.views;

import org.eclipse.swt.widgets.Control;


public enum LayoutLocation {
	NORTH,
	SOUTH,
	CENTRE,
	EAST,
	WEST;


	public void setLayoutData(final Control view) {
		view.setLayoutData(this);
	}

} // end enum LayoutLocation
