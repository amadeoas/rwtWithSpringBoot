package uk.co.bocaditos.rwtwithspringboot.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;


/**
 * Menu on left and view sites on the rest.
 * 
 * the rest is defined within the menu class.
 * 
 * @author aasco
 */
@SuppressWarnings("serial")
public class MainView extends Composite {

	private static final int MIN_WIDTH_LEFT = 150;
	private static final int MIN_WIDTH_RIGHT = 50;
	
	public static final int VIEW_INDEX_LOGIN = 0;
	public static final int VIEW_INDEX_APP   = 1;
	
	public static final int VIEW_INDEX_MENU = 0;
	public static final int VIEW_INDEX_VIEW = 0;


	public MainView(final Composite parent) {
		super(parent, SWT.NONE);

		setLayout(new FillLayout());
		new MainMenuView(this);

		addListener(SWT.Resize, new Listener() {

			@Override
	        public void handleEvent(final Event evt) {
				final SashForm form = (SashForm) MainView.this.getChildren()[0];
	            final int width = getClientArea().width;
	            final int[] weights = form.getWeights();

	            if (width >= MIN_WIDTH_LEFT + MIN_WIDTH_RIGHT) {
	                weights[0] = 1000000 * MIN_WIDTH_LEFT / width;
	                weights[1] = 1000000 - weights[0];
	            } else {
	                weights[0] = 1000000 * MIN_WIDTH_LEFT / (MIN_WIDTH_LEFT + MIN_WIDTH_RIGHT);
	                weights[1] = 1000000 * MIN_WIDTH_RIGHT / (MIN_WIDTH_LEFT + MIN_WIDTH_RIGHT);
	            }
	            form.setWeights(weights);
	        }

		});
	}

	public boolean isLoggedIn() {
		return ((LoginView) getParent().getChildren()[0]).isLoggedIn();
	}
	
	public static boolean setVisible(final Composite views, final int index) {
		if (index < 0 || index >= views.getChildren().length) {
			// Nothing to do
			// TODO: report issue

			return false;
		}

		final Control ctrl = views.getChildren()[index];

		if (views instanceof Views) {
			final Views v = (Views) views;
			final MainView mainView = (MainView) v.getParent().getParent();

			if (((BaseView) ctrl).needs2Login() && !v.isLoggedIn()) {
				// Not logged and must be
				// TODO: log message
				mainView.showLogIn(index);

				return false;
			}
			
			// Make sure that the APP view is displayed
			final StackLayout layout = ((StackLayout) mainView.getParent().getLayout());

			if (layout.topControl == null || layout.topControl instanceof LoginView) {
				layout.topControl = mainView;
				mainView.getParent().layout();
			}
		} else if (ctrl instanceof MainView) {
			final MainView mainView = (MainView) ctrl;
			final MainMenuView menu =  (MainMenuView) ((SashForm) mainView.getChildren()[0]).getChildren()[0];
			final LoginView login = (LoginView) views.getChildren()[MainView.VIEW_INDEX_LOGIN];

			menu.setVisible(login.getViewIndex());
		} else {
			// Login
			// Remove selected menu
			final LoginView login = (LoginView) ctrl;
			final MainMenuView menu 
					= (MainMenuView) ((SashForm) ((MainView) views.getChildren()[MainView.VIEW_INDEX_APP])
						.getChildren()[VIEW_INDEX_MENU]).getChildren()[0];
			final int viewIndex = menu.clearSelection();

			login.setViewIndex(viewIndex);
		}
		((StackLayout) views.getLayout()).topControl = ctrl;
		views.layout();

		return true;
	}
	
	void showLogIn(final int viewIndex) {
		((LoginView) getParent().getChildren()[VIEW_INDEX_LOGIN]).setViewIndex(viewIndex);
		setVisible(getParent(), VIEW_INDEX_LOGIN);
	}
	
	MainMenuView getMainMenu() {
		return (MainMenuView) ((SashForm) getChildren()[0]).getChildren()[VIEW_INDEX_MENU];
	}
	
	void setVisible(final int viewIndex) {
		if (setVisible(getParent(), VIEW_INDEX_APP)) {
			final Control[] ctrls = ((SashForm) getChildren()[0]).getChildren();

			((MainMenuView) ctrls[0]).setVisible(viewIndex);
		}
	}

} // end class MainView
