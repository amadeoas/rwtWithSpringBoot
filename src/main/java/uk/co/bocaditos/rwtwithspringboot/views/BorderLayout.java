package uk.co.bocaditos.rwtwithspringboot.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;


/**
 * Lays out a composite, arranging and resizing its components to fit in the five regions: north, 
 * south, east, west, and centre.
 * 
 * http://www.java2s.com/Code/Java/SWT-JFace-Eclipse/BorderLayoutSample.htm
 */
@SuppressWarnings("serial")
public class BorderLayout extends Layout {

	// Controls in all the regions
	private Control[] controls = new Control[5];

	// Cached sizes
	private Point[] sizes;

	// Preferred width and height
	private int width;
	private int height;


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.widgets.Layout#computeSize(org.eclipse.swt.widgets.Composite,
	 *      int, int, boolean)
	 */
	protected final Point computeSize(final Composite composite, int wHint, int hHint, 
			boolean flushCache) {
		if (this.sizes == null || flushCache == true) {
			refreshSizes(composite.getChildren());
		}

		int w = wHint;
		int h = hHint;

		if (w == SWT.DEFAULT) {
			w = this.width;
		}
		if (h == SWT.DEFAULT) {
			h = this.height;
		}

		return new Point(w, h);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.widgets.Layout#layout(org.eclipse.swt.widgets.Composite,
	 *      boolean)
	 */
	protected final void layout(Composite composite, boolean flushCache) {
		if (flushCache || this.sizes == null) {
			refreshSizes(composite.getChildren());
		}

		final Rectangle clientArea = composite.getClientArea();

		// Enough space for all
		if (this.controls[LayoutLocation.NORTH.ordinal()] != null) {
			this.controls[LayoutLocation.NORTH.ordinal()].setBounds(clientArea.x, clientArea.y, 
					clientArea.width, this.sizes[LayoutLocation.NORTH.ordinal()].y);
		}
		if (this.controls[LayoutLocation.SOUTH.ordinal()] != null) {
			this.controls[LayoutLocation.SOUTH.ordinal()].setBounds(clientArea.x, 
					clientArea.y + clientArea.height - this.sizes[LayoutLocation.SOUTH.ordinal()].y,
					clientArea.width, this.sizes[LayoutLocation.SOUTH.ordinal()].y);
		}
		if (this.controls[LayoutLocation.WEST.ordinal()] != null) {
			this.controls[LayoutLocation.WEST.ordinal()].setBounds(clientArea.x, 
					clientArea.y + this.sizes[LayoutLocation.NORTH.ordinal()].y, 
					this.sizes[LayoutLocation.WEST.ordinal()].x, 
					clientArea.height - this.sizes[LayoutLocation.NORTH.ordinal()].y - this.sizes[LayoutLocation.SOUTH.ordinal()].y);
		}
		if (this.controls[LayoutLocation.EAST.ordinal()] != null) {
			this.controls[LayoutLocation.EAST.ordinal()].setBounds(clientArea.x + clientArea.width - this.sizes[LayoutLocation.EAST.ordinal()].x, 
					clientArea.y + this.sizes[LayoutLocation.NORTH.ordinal()].y, 
					this.sizes[LayoutLocation.EAST.ordinal()].x,
					clientArea.height - this.sizes[LayoutLocation.NORTH.ordinal()].y - this.sizes[LayoutLocation.SOUTH.ordinal()].y);
		}
		if (this.controls[LayoutLocation.CENTRE.ordinal()] != null) {
			this.controls[LayoutLocation.CENTRE.ordinal()].setBounds(clientArea.x + this.sizes[LayoutLocation.WEST.ordinal()].x, 
					clientArea.y + this.sizes[LayoutLocation.NORTH.ordinal()].y, 
					clientArea.width - this.sizes[LayoutLocation.WEST.ordinal()].x - this.sizes[LayoutLocation.EAST.ordinal()].x, 
					clientArea.height - this.sizes[LayoutLocation.NORTH.ordinal()].y - this.sizes[LayoutLocation.SOUTH.ordinal()].y);
		}
	}

	protected final void refreshSizes(final Control[] children) {
		populateCtrls(children);

		this.sizes = buildSizes(this.sizes, this.controls);

		this.width = 0;
		this.width = Math.max(width, this.sizes[LayoutLocation.NORTH.ordinal()].x);
		this.width = Math.max(width, 
				this.sizes[LayoutLocation.WEST.ordinal()].x + this.sizes[LayoutLocation.CENTRE.ordinal()].x + this.sizes[LayoutLocation.EAST.ordinal()].x);
		this.width = Math.max(width, this.sizes[LayoutLocation.SOUTH.ordinal()].x);

		this.height = Math.max(
				Math.max(this.sizes[LayoutLocation.WEST.ordinal()].y, this.sizes[LayoutLocation.EAST.ordinal()].y), this.sizes[LayoutLocation.CENTRE.ordinal()].y) 
				+ this.sizes[LayoutLocation.NORTH.ordinal()].y + this.sizes[LayoutLocation.SOUTH.ordinal()].y;
	}
	
	protected Point[] buildSizes(Point[] sizes, final Control[] controls) {
		if (sizes == null) {
			sizes = new Point[5];
		}

		for (int i = 0; i < controls.length; ++i) {
			final Control control = controls[i];

			if (control == null) {
				sizes[i] = new Point(0, 0);
			} else {
				sizes[i] = control.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
			}
		}

		return sizes;
	}
	
	private void populateCtrls(final Control[] children) {
		for (final Control ctrl : children) {
			final Object layoutData = ctrl.getLayoutData();

			if (layoutData == null || (!(layoutData instanceof LayoutLocation))) {
				continue;
			}
			this.controls[((LayoutLocation) layoutData).ordinal()] = ctrl;
		}
	}

} // end class BorderLayout
