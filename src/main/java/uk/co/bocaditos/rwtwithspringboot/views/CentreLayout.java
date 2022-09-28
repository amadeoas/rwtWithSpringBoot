package uk.co.bocaditos.rwtwithspringboot.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


/**
 * Support to centre a Composite. After setting this layout to the parent component of the view to 
 * centre the call setLayoutData with the Composite view to centre.
 * 
 * @author aasco
 */
@SuppressWarnings("serial")
public class CentreLayout extends BorderLayout {
	
	private final Point size;


	public CentreLayout(final Composite parent, final int centreWidth, final int centreHeight) {
		Composite comp;

		comp = new Composite(parent, SWT.NONE);
		comp.setLayoutData(LayoutLocation.NORTH);
		comp = new Composite(parent, SWT.NONE);
		comp.setLayoutData(LayoutLocation.SOUTH);
		comp = new Composite(parent, SWT.NONE);
		comp.setLayoutData(LayoutLocation.WEST);
		comp = new Composite(parent, SWT.NONE);
		comp.setLayoutData(LayoutLocation.EAST);
		
		this.size = new Point(centreWidth, centreHeight);
	}

	public static void setLayoutData(final Composite view) {
		if (view.getParent().getChildren().length != 5) {
			throw new RuntimeException(
					"Invalid number of childern for BorderLayout before adding new views; there are " 
						+ view.getParent().getChildren().length + " and there should be 5");
		}
		if (!(view.getParent().getLayout() instanceof CentreLayout)) {
			throw new RuntimeException("Invalid layout; it should be CentreLayout");
		}

		for (final Control ctrl : view.getParent().getChildren()) {
			if (ctrl == view) {
				LayoutLocation.CENTRE.setLayoutData(view);

				return;
			}
		}

		throw new RuntimeException(
				"Invalid childern for BorderLayout; it isn't part of the childrent so cannot be " 
				+ "called setLayoutData");
	}

	@Override
	protected Point[] buildSizes(Point[] sizes, final Control[] controls) {
		if (sizes == null) {
			sizes = new Point[5];
			sizes[LayoutLocation.CENTRE.ordinal()] = this.size;
		}

		final Rectangle clientArea = controls[0].getParent().getClientArea();

		sizes[LayoutLocation.NORTH.ordinal()] = sizes[LayoutLocation.SOUTH.ordinal()] 
				= new Point(clientArea.width, 
					Math.max((clientArea.height - sizes[LayoutLocation.CENTRE.ordinal()].y)/2, 0));
		sizes[LayoutLocation.WEST.ordinal()] = sizes[LayoutLocation.EAST.ordinal()] 
				= new Point(Math.max((clientArea.width - sizes[LayoutLocation.CENTRE.ordinal()].x)/2, 0), 
					sizes[LayoutLocation.CENTRE.ordinal()].y);

		return sizes;
	}

} // end class CentreLayout
