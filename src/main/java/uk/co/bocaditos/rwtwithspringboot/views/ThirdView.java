package uk.co.bocaditos.rwtwithspringboot.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


@SuppressWarnings("serial")
public class ThirdView extends BaseView {

	ThirdView(Composite parent) {
		super(parent);
	}

	@Override
	public boolean needs2Login() {
		return true;
	}

	@Override
	public String getTitle() {
		return title();
	}

	public static String title() {
		return "Third View";
	}

	public static String description() {
		return "This is the THIRD view in the demo. The view needs to be added some funtionality, " 
				+ "i.e. meaning, by opulatig in with other controls. It has been set a requiring " 
				+ "to login so when selected it would have first to login before this wiew will be " 
				+ "visibke.";
	}

	@Override
	public Control buildView(final Object... args) {
		// TODO: Complete view
		final Composite view = new Composite(this, SWT.NONE);

		return view;
	}

} // end class ThirdView
