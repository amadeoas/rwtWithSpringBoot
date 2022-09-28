package uk.co.bocaditos.rwtwithspringboot.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import uk.co.bocaditos.rwtwithspringboot.config.Messages;


@SuppressWarnings("serial")
public class AboutView extends BaseView {

	AboutView(final Composite parent) {
		super(parent);
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

		view.setText(Messages.get().aboutDescription);

		return view;
	}

} // end class AboutView
