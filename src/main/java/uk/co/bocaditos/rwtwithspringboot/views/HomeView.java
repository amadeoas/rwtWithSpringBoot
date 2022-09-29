package uk.co.bocaditos.rwtwithspringboot.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import uk.co.bocaditos.rwtwithspringboot.config.Messages;


@SuppressWarnings("serial")
public class HomeView extends BaseView {


	HomeView(final Composite parent) {
		super(parent);
	}

	@Override
	public boolean needs2Login() {
		return false;
	}

	@Override
	public String getTitle() {
		return Messages.get().homeTitle;
	}

	@Override
	public String getId() {
		return "home";
	}

	@Override
	public Control buildView(final Object... args) {
		final Composite view = new Composite(this, SWT.NONE);
		RowLayout rowLayout;
		final Text description;
		final Composite centreView;
		Card card;

		// Description section
		view.setLayout(new BorderLayout());
		description = new Text(view, SWT.MULTI | SWT.READ_ONLY | SWT.WRAP);
		description.setText(Messages.get().homeDescription);
		description.setLayoutData(new GridData( GridData.FILL_HORIZONTAL)); // with this in addition, it will wrap
		LayoutLocation.NORTH.setLayoutData(description);

		// Cards section
		centreView = new Composite(view, SWT.NONE);
		LayoutLocation.CENTRE.setLayoutData(centreView);
		rowLayout = new RowLayout();
		rowLayout = new RowLayout();
	    rowLayout.wrap = true;
	    rowLayout.pack = false;
	    rowLayout.justify = true;
	    rowLayout.marginLeft = 5;
	    rowLayout.marginTop = 5;
	    rowLayout.marginRight = 5;
	    rowLayout.marginBottom = 5;
	    rowLayout.spacing = 0;
		centreView.setLayout(rowLayout);

		card = new Card(centreView, SWT.NONE, FirstView.title());
		card.buildTextBodyView(FirstView.description());
		addButtonListener(card, 0);

		card = new Card(centreView, SWT.NONE, SecondView.title());
		card.buildTextBodyView(SecondView.description());
		addButtonListener(card, 1);
		
		card = new Card(centreView, SWT.NONE, ThirdView.title());
		card.buildTextBodyView(ThirdView.description());
		addButtonListener(card, 2);

		return view;
	}
	
	private void addButtonListener(final Card card, final int buttonIndex) {
		card.setButtonListener(buttonIndex, new SelectionListener() {

			@Override
			public void widgetSelected(final SelectionEvent se) {
				Composite ctrl = getParent();

				ctrl = ctrl.getParent().getParent();
				final MainMenuView menu = ((MainView) ctrl).getMainMenu();

				menu.setView(buttonIndex + 1);
			}

			@Override
			public void widgetDefaultSelected(final SelectionEvent se) {
				// Nothing to do
			}

		});
	}

} // end class HomeView
