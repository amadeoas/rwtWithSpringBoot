package uk.co.bocaditos.rwtwithspringboot.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;


/**
 * Menu view.
 * 
 * TODO: use https://www.eclipse.org/nebula/
 * 
 * @author aasco
 */
@SuppressWarnings("serial")
public class MainMenuView extends Composite {

	public static final int INDEX_HEADER = 0;
	public static final int INDEX_MENU 	 = 1;
	public static final int INDEX_FOOTER = 2;

	static final String MENU_LOGOUT = "Logout";
	static final String MENU_LOGIN  = "Login  ";
	
	// Views indexes
	public static int VIEW_INDEX_HOME = 0;
	public static int VIEW_INDEX_CANDIDATE_SEARCH = 1;
	public static int VIEW_INDEX_CLEANSE_MATCH = 2;
	public static int VIEW_INDEX_DATA_ACCESS = 3;
	public static int VIEW_INDEX_ABOUT = 4;

	
	/**
	 * Representation of a menu item, clickable/selectable.
	 * 
	 * @author aasco
	 */
	class MenuItem extends Label {
	
		private final BaseView view;
		private boolean selected = false;

	
		public MenuItem(final Composite parent, final String text, final int viewIndex) {
			this(parent, text, viewIndex, SWT.ICON_ERROR - 1);
		}

		public MenuItem(final Composite parent, final String text, final int viewIndex, 
				final int sysIcon) {
			super(parent, SWT.PUSH);

			if (viewIndex < 0 || viewIndex >= MainMenuView.this.views.getChildren().length) {
				this.view = null;
			} else {
				this.view = (BaseView) MainMenuView.this.views.getChildren()[viewIndex];
			}
			setText(text);
			if (sysIcon >= SWT.ICON_ERROR) {
				// e.g. SWT.ICON_QUESTION
				setImage(parent.getDisplay().getSystemImage(SWT.ICON_QUESTION));
			}

			addMouseListener(new MouseListener() {

				@Override
				public void mouseDoubleClick(final MouseEvent me) {
					// Nothing to do
				}

				@Override
				public void mouseDown(final MouseEvent me) {
					// Nothing to do
				}

				@Override
				public void mouseUp(final MouseEvent me) {
					// A click
					final MenuItem item = (MenuItem) me.getSource();

					if (!item.isEnabled() 
							|| (item.isSelected() && ((StackLayout) MainMenuView.this.getParent().getParent().getParent().getLayout()).topControl != null)) {
						return;
					}

					if (MENU_LOGOUT == item.getText() || MENU_LOGIN == item.getText()) {
						// TODO: Move to Login page
						final Composite parent = MainMenuView.this.getParent();
						final Composite p = parent.getParent().getParent();

						MainView.setVisible(p, MainView.VIEW_INDEX_LOGIN);

						return;
					}

					final Control[] ctrls = MenuItem.this.getParent().getChildren();
					int indexView = -1;
					MenuItem prevSelected = null;
	
					for (int index = 0; index < ctrls.length; ++index) {
						final MenuItem it = (MenuItem) ctrls[index];

						if (indexView == -1 && it == item) {
							indexView = index;

							continue;
						} else if (it.isSelected()) {
							prevSelected = it;
						}
					}

					if (indexView > -1) {
						if (prevSelected != null) {
							prevSelected.setSelected(false);
						}
						item.setSelected(true);
						MainMenuView.this.views.setVisible(indexView);
					}
				}
			});
		}
		
		public final boolean needs2Login() {
			return (this.view == null) ? false :this.view.needs2Login();
		}
	
		public final boolean isSelected() {
			return this.selected;
		}
		
		void setSelected(final boolean selected) {
			final FontData fontData = getFont().getFontData()[0];
			final Font font = new Font(getDisplay(), new FontData(fontData.getName(), 
					fontData.getHeight(), 
					selected ? SWT.BOLD | SWT.COLOR_GRAY : SWT.NORMAL | SWT.COLOR_BLACK));

			setFont(font);
			this.selected = selected;
		}
	
	} // end class MenuItem


	private final Composite header;
	private final Composite menu;
	private final Composite footer;

	private final Views views;
	private MenuItem logInOut;


	/**
	 * Creates the panel.
	 */
	MainMenuView(final MainView parent) {
		super(new SashForm(parent, SWT.HORIZONTAL), SWT.CENTER);

		setLayout(new BorderLayout());

		this.views = new Views(getParent());

		this.header = buildHeader();
		this.menu 	= buildMenu();
		this.footer = buildFooter();

		((SashForm) getParent()).setWeights(new int[] {1, 4});
	}
	
	public void setVisible(final int viewIndex) {
		if (viewIndex < 0 || viewIndex > this.menu.getChildren().length) {
			// Nothing to do
			// TODO: log/report issue

			return;
		}

		final Control[] ctrls = this.views.getChildren();
		final BaseView view = (BaseView) ctrls[viewIndex];

		if (view.needs2Login() && !((MainView) getParent().getParent()).isLoggedIn()) {
			// Login first
			// TODO: log message
			((MainView) getParent()).showLogIn(viewIndex);

			return;
		}

		final MenuItem menuItem = (MenuItem) this.menu.getChildren()[viewIndex];
		final Event event = new Event();

		event.index = viewIndex;
		event.item = menuItem;
		event.text = menuItem.getText();
		event.time = (int) System.currentTimeMillis();
		event.doit = true;
		event.widget = menuItem;
		event.display = menuItem.getDisplay();
		event.type = SWT.MouseUp;
		menuItem.notifyListeners(SWT.MouseUp, event);
	}
	
	MenuItem getLogInOut() {
		return this.logInOut;
	}

	void setLogInOut(final String txt) {
		this.logInOut.setText(txt);
	}
	
	int clearSelection() {
		int selectedIndex = VIEW_INDEX_HOME;

		for (int index = 0; index < this.menu.getChildren().length; ++index) {
			final Control ctrl = this.menu.getChildren()[index];
			final MenuItem item = ((MenuItem) ctrl);

			if (item.isSelected()) {
				selectedIndex = index;
			}
			item.setSelected(false);
		}

		return selectedIndex;
	}

	Composite getComponent(final int index) {
		final Composite comp;

		switch (index) {
			case INDEX_HEADER: 
				comp = this.header;

				break;

			case INDEX_MENU: 
				comp = this.menu;

				break;

			case INDEX_FOOTER: 
				comp = this.footer;

				break;

			default: 
				comp = null;
		}

		return comp;
	}
	
	private Composite buildHeader() {
		final Composite panel = new Composite(this, SWT.LEFT_TO_RIGHT);

		panel.setLayout(new GridLayout(1, false));
		LayoutLocation.NORTH.setLayoutData(panel);

		// Header
		final Composite header = new Composite(panel, SWT.LEFT_TO_RIGHT);

		header.setLayout(new FillLayout(SWT.HORIZONTAL));

		// TODO: Icon
		
		// App name
		final Label label = new Label(header, SWT.LEFT);
		
		label.setText("Testing");
		
		// Separator
		final Label separator = new Label(panel, SWT.SEPARATOR | SWT.SHADOW_OUT | SWT.HORIZONTAL);

		separator.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		return panel;
	}
	
	private Composite buildMenu() {
		final Composite m = new Composite(this, SWT.LEFT_TO_RIGHT);
		final Composite menu = new Composite(m, SWT.NONE);
		final GridLayout layout = new GridLayout(1, true);

		m.setLayout(new FillLayout(SWT.VERTICAL));
		LayoutLocation.CENTRE.setLayoutData(m);
		layout.marginLeft = 2;
		layout.marginRight = 1;
		menu.setLayout(layout);
		buildMenuItem(menu, "Home ");
		buildMenuItem(menu, "Candidate Search   ");
		buildMenuItem(menu, "Cleanse Match  ");
		buildMenuItem(menu, "Forge Data ");
		buildMenuItem(menu, "About ");
		

		return menu;
	}
	
	private Composite buildFooter() {
		final Composite panel = new Composite(this, SWT.LEFT_TO_RIGHT);

		panel.setLayout(new GridLayout(1, false));
		LayoutLocation.SOUTH.setLayoutData(panel);
		
		// Separator
		final Label separator = new Label(panel, SWT.SEPARATOR | SWT.SHADOW_OUT | SWT.HORIZONTAL);

		separator.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		// Footer
		final Composite menu = new Composite(panel, SWT.LEFT_TO_RIGHT);

		menu.setLayout(new FillLayout(SWT.VERTICAL));

		// Popup to confirm log out
		
		// Log in view. Remember to clear all previous values
		this.logInOut = buildLoginMenuItem(menu);

		return menu;
	}
	
	private MenuItem buildMenuItem(final Composite mainMenuBar, final String text) {
		return new MenuItem(mainMenuBar, text, this.views.getChildren().length);
	}
	
	private MenuItem buildLoginMenuItem(final Composite mainMenuBar) {
		return new MenuItem(mainMenuBar, MENU_LOGIN, -1) {

			@Override
			public void setSelected(final boolean selected) {
				// Don't do anything
			}

		};
	}

} // end class MainMenuView


/**
 * Component with all views.
 */
@SuppressWarnings("serial")
class Views extends Composite {

	Views(final Composite parent) {
		super(parent, SWT.NONE | SWT.BORDER);

		setLayout(new StackLayout());
		buildViews();
	}
	
	public boolean isLoggedIn() {
		return ((LoginView) getParent().getParent().getParent().getChildren()[0]).isLoggedIn();
	}

	boolean isVisible(final int index) {
		if (index < getChildren().length) {
			return getChildren()[index].isVisible();
		}

		return false;
	}

	void setVisible(final int index) {
		MainView.setVisible(this, index);
	}

	private void buildViews() {
		new HomeView(this);
		new FirstView(this);
		new SecondView(this);
		new ThirdView(this);
		new AboutView(this, "This Website allows to interrogate any of three APIs and shows their " 
				+ "corresponding replies.");
	}

} // end class Views
