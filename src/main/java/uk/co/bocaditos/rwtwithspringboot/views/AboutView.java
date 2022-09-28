package uk.co.bocaditos.rwtwithspringboot.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;


@SuppressWarnings("serial")
public class AboutView extends BaseView {

	AboutView(final Composite parent, final String text) {
		super(parent, text);
	}

	@Override
	public boolean needs2Login() {
		return false;
	}

	@Override
	public String getTitle() {
		return "About";
	}

	@Override
	public Control buildView(final Object... args) {
		final Text view = new Text(this, SWT.CENTER);

		view.setText((String) args[0]);

		return view;
	}

} // end class AboutView
