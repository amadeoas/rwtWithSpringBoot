package uk.co.bocaditos.rwtwithspringboot.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import uk.co.bocaditos.rwtwithspringboot.config.Messages;


@SuppressWarnings("serial")
public class HomeView extends BaseView {
	
	private static final int NUM_CARDS = 3;
	
	private static final int MARGIN_LEFT  = 5;
	private static final int MARGIN_RIGHT = 5;


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
	public Control buildView(final Object... args) {
		final Composite view = new Composite(this, SWT.NONE);
		final Composite centreView;
		Card card;

		view.setLayout(new CentreLayout(view, 
				(Card.MIN_WIDTH * NUM_CARDS) + ((MARGIN_LEFT + MARGIN_RIGHT) * (NUM_CARDS + 1)), 
				Card.MIN_HEIGHT));

		centreView = new Composite(view, SWT.NONE);
		centreView.setLayout(new FillLayout());
		CentreLayout.setLayoutData(centreView);

		card = new Card(centreView, SWT.NONE, FirstView.title());
		card.buildTextBodyView(FirstView.description());

		card = new Card(centreView, SWT.NONE, SecondView.title());
		card.buildTextBodyView(SecondView.description());
		
		card = new Card(centreView, SWT.NONE, ThirdView.title());
		card.buildTextBodyView(ThirdView.description());

		return view;
	}

} // end class HomeView
