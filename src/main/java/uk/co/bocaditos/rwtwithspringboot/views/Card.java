package uk.co.bocaditos.rwtwithspringboot.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import uk.co.bocaditos.rwtwithspringboot.config.Messages;


@SuppressWarnings("serial")
public class Card extends Composite {

	public static final int MIN_WIDTH  = 200;
	public static final int MIN_HEIGHT = 260;
	public static final int TOP_BOTTOM_HEIGHT = 40; // minimum height for the control

	private Label titleView;
	private Composite bodyView;
	private Composite buttonsView;


	public Card(final Composite parent, final int style, final String title, 
			final String... buttonLabels) {
		super(parent, style);

	    setBounds(0, 0, MIN_WIDTH, MIN_HEIGHT);
		setLayout(new BorderLayout());

	    this.titleView = buildTopView(this, title);
	    this.bodyView = buildBodyView(this);
	    this.buttonsView = buildBottomView(this, buttonLabels);
	}
	
	public void setButtonListener(final int buttonIndex ,final SelectionListener listener) {
		if (buttonIndex < 0 || buttonIndex >= this.buttonsView.getChildren().length) {
			// TODO: log message

			return;
		}
		((Button) this.buttonsView.getChildren()[buttonIndex]).addSelectionListener(listener);
	}
	
	public void setTitle(final String title) {
		this.titleView.setText(title);
	}
	
	public String getTitle() {
		return (this.titleView == null) ? null : this.titleView.getText();
	}

	public Button addButton(final int style) {
		return new Button(this.buttonsView, style);
	}
	
	public Composite getBodyView() {
		return this.bodyView;
	}
	
	public Text buildTextBodyView(final String text) {
		if (this.bodyView.getChildren().length > 0) {
			final Control ctrl = this.bodyView.getChildren()[0];

			if (ctrl instanceof Text) {
				return (Text) ctrl;
			}
			
			return null;
		}

		final Text textView 
				= new Text(this.bodyView, 
						SWT.MULTI | SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL); // SWT.MULTI, SWT.WRAP, SWT.READ_ONLY
		
		if (text != null) {
			textView.setText(text);
		}
		textView.setBounds(0, 0, MIN_WIDTH, MIN_HEIGHT - (2 * TOP_BOTTOM_HEIGHT));

		return textView;
	}

	private Composite buildBodyView(final Composite parent) {
		if (this.bodyView != null) {
			return this.bodyView;
		}

		final Composite bodyPanel = new Composite(parent, SWT.LEFT_TO_RIGHT);

		LayoutLocation.CENTRE.setLayoutData(bodyPanel);

		return bodyPanel;
	}

	private Label buildTopView(final Composite parent, final String title) {
		if (this.titleView != null) {
			return this.titleView;
		}

		final Composite topPanel = new Composite(parent, SWT.LEFT_TO_RIGHT);

		topPanel.setLayout(new GridLayout(1, false));
		LayoutLocation.NORTH.setLayoutData(topPanel);

		final Composite panel = new Composite(topPanel, SWT.LEFT_TO_RIGHT);
		final Label titleView = new Label(panel, SWT.CENTER);
		final FontData fontData = titleView.getFont().getFontData()[0];
		final Font font = new Font(getDisplay(), 
				new FontData(fontData.getName(), fontData.getHeight(), SWT.BOLD));
		final Label separator;

		panel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		panel.setLayout(new BorderLayout());
		titleView.setFont(font);
		titleView.setText(title);
		LayoutLocation.CENTRE.setLayoutData(titleView);
		
		// Separator
		separator = new Label(topPanel, SWT.SEPARATOR | SWT.SHADOW_OUT | SWT.HORIZONTAL);
		separator.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		topPanel.setBounds(0, 0, MIN_WIDTH, TOP_BOTTOM_HEIGHT);

		return titleView;
	}
	
	private Composite buildBottomView(final Composite parent, final String... buttonLabels) {
		if (this.buttonsView != null) {
			return this.buttonsView;
		}

		final Composite bottomPanel = new Composite(parent, SWT.LEFT_TO_RIGHT);
		final Label separator;

		bottomPanel.setLayout(new GridLayout(1, false));
		LayoutLocation.SOUTH.setLayoutData(bottomPanel);

		// Separator
		separator = new Label(bottomPanel, SWT.SEPARATOR | SWT.SHADOW_OUT | SWT.HORIZONTAL);
		separator.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		// Footer
		final Composite buttonsView = new Composite(bottomPanel, SWT.NONE);

		buttonsView.setLayoutData(new GridData(SWT.FILL, SWT.RIGHT, true, false));
		if (buttonLabels == null || buttonLabels.length == 0) {
			final Button btn = new Button(buttonsView, SWT.PUSH);
			
			btn.setText(Messages.get().cardBtnTitle);
			btn.setBounds(0, 0, (MIN_WIDTH - 20), TOP_BOTTOM_HEIGHT);
		} else {
			for (final String btnLabel : buttonLabels) {
				final Button btn = new Button(buttonsView, SWT.PUSH);

				btn.setText(btnLabel);
				btn.setBounds(0, 0, (MIN_WIDTH - 20) / buttonLabels.length, TOP_BOTTOM_HEIGHT);
			}
		}
		bottomPanel.setBounds(0, 0, MIN_WIDTH, TOP_BOTTOM_HEIGHT);

		return buttonsView;
	}

} // end class Card
