package uk.co.bocaditos.rwtwithspringboot.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;


@SuppressWarnings("serial")
public abstract class BaseView extends Composite {

	BaseView(final Composite parent, final Object... args) {
		this(parent, SWT.NONE, args);
	}

	BaseView(final Composite parent, int style, final Object... args) {
		super(parent, style);

		setLayout(new BorderLayout());

		buildTopView(args);
		LayoutLocation.CENTRE.setLayoutData(buildView(args));
		buildBottomView(args);
	}

	public abstract boolean needs2Login();

	public abstract String getTitle();

	public abstract Control buildView(final Object... args);

	public boolean hasFootter() {
		return false;
	}

	public Control buildFootter(final Composite parent, final Object... args) {
		return null;
	}
	
	private Composite buildTopView(final Object... args) {
		final Composite topPanel = new Composite(this, SWT.LEFT_TO_RIGHT);

		topPanel.setLayout(new GridLayout(1, false));
		LayoutLocation.NORTH.setLayoutData(topPanel);

		final Label title = new Label(topPanel, SWT.CENTER);
		final FontData fontData = title.getFont().getFontData()[0];
		final Font font = new Font(getDisplay(), 
				new FontData(fontData.getName(), fontData.getHeight(), SWT.BOLD));
		final Label separator;

		title.setFont(font);
		title.setText(getTitle());
		
		// Separator
		separator = new Label(topPanel, SWT.SEPARATOR | SWT.SHADOW_OUT | SWT.HORIZONTAL);
		separator.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		return topPanel;
	}
	
	private Composite buildBottomView(final Object... args) {
		if (!hasFootter()) {
			return null;
		}

		final Composite bottomPanel = new Composite(this, SWT.LEFT_TO_RIGHT);
		final Label separator;

		bottomPanel.setLayout(new GridLayout(1, false));
		LayoutLocation.SOUTH.setLayoutData(bottomPanel);

		// Separator
		separator = new Label(bottomPanel, SWT.SEPARATOR | SWT.SHADOW_OUT | SWT.HORIZONTAL);
		separator.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		// Footer
		buildFootter(bottomPanel, args);

		return bottomPanel;
	}

} // end class BaseView
