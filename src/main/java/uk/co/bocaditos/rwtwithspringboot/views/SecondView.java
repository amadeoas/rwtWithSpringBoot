package uk.co.bocaditos.rwtwithspringboot.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import uk.co.bocaditos.rwtwithspringboot.config.Messages;


@SuppressWarnings("serial")
public class SecondView extends BaseView {

	SecondView(final Composite parent) {
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

	@Override
	public String getId() {
		return "second";
	}

	public static String title() {
		return Messages.get().secondViewTitle;
	}

	public static String description() {
		return Messages.get().secondViewDescription;
	}

	@Override
	public Control buildView(final Object... args) {
		// TODO: Complete view
		final Composite view = new Composite(this, SWT.NONE);

		return view;
	}

} // end class SecondView
