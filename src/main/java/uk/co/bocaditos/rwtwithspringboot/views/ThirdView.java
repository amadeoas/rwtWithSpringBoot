package uk.co.bocaditos.rwtwithspringboot.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import uk.co.bocaditos.rwtwithspringboot.config.Messages;


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
		return Messages.get().thirdViewTitle;
	}

	public static String description() {
		return Messages.get().thirdViewDescription;
	}

	@Override
	public Control buildView(final Object... args) {
		// TODO: Complete view
		final Composite view = new Composite(this, SWT.NONE);

		return view;
	}

} // end class ThirdView
