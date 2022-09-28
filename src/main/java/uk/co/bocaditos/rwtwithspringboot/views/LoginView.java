package uk.co.bocaditos.rwtwithspringboot.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import uk.co.bocaditos.rwtwithspringboot.views.MainMenuView.MenuItem;


@SuppressWarnings("serial")
public class LoginView extends Composite {

	public static final int MIN_WIDTH  = 400;
	public static final int MIN_HEIGHT = 400;
	
	private static final long AUTH_TIME = 1 * 30 * 1000; // milliseconds
	
	private final Text username;
	private final Text password;
	
	private boolean loggedIn = false;
	private int viewIndex = MainMenuView.VIEW_INDEX_HOME; // home
	
	private Thread thread;


	public LoginView(final Composite parent) {
		super(parent, SWT.NONE);

		setLayout(new CentreLayout(this, 200, 300));

		final GridData gridData = new GridData();
		final Composite view = new Composite(this, SWT.CENTER | SWT.BORDER);
		Label label;
		
		view.setLayout(new FillLayout(SWT.VERTICAL));
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.CENTER;
		gridData.verticalAlignment = GridData.CENTER;
		CentreLayout.setLayoutData(view);
		
		label = new Label(view, SWT.NONE);
		label.setText("Username:");
		this.username = new Text(view, SWT.SINGLE | SWT.BORDER);
		this.username.setSize(200, 20);

		label = new Label(view, SWT.NONE);
		label.setText("Password:");
		// Create a Password field
		this.password = new Text(view, SWT.SINGLE | SWT.BORDER | SWT.PASSWORD);
		// Set echo char
		this.password.setEchoChar('*');
		this.password.setSize(400, 20);

		final Button button = new Button(view, SWT.PUSH);

		button.setText(MainMenuView.MENU_LOGIN);
		button.addSelectionListener(new SelectionAdapter() {

	            @Override
	            public void widgetSelected(final SelectionEvent se) {
	        	   	if (!LoginView.this.username.getText().isEmpty() 
	        	   			&& !LoginView.this.password.getText().isEmpty() 
	           				&& verifyLoggin()) {
	        	   		final MainMenuView menu = getMenuView();
	        	   		
	       	   			LoginView.this.loggedIn = true;
	       	   			menu.setLogInOut(MainMenuView.MENU_LOGOUT);
	       	   			menu.setVisible(LoginView.this.viewIndex);
	       	   			clear();
	       			}
	            }

		});
		
		setSize(MIN_WIDTH, MIN_HEIGHT);
	}
	
	public int getViewIndex() {
		return this.viewIndex;
	}
	
	public boolean isLoggedIn() {
		return this.loggedIn;
	}
	
	public void display(final int viewIndex) {
		final Control ctrl = getParent().getChildren()[MainView.VIEW_INDEX_APP];
		final Composite parent = (Composite) ((Composite) ctrl).getChildren()[0];

		this.viewIndex = viewIndex;
		this.loggedIn = false;
		((MainMenuView) parent.getChildren()[0]).setLogInOut(MainMenuView.MENU_LOGIN);
		clear();
		setVisible(true);

		final Thread thread = this.thread;

		if (thread != null) {
			thread.interrupt();
		}
	}
	
	void logout() {
		final MenuItem menuItem = ((MainView) getParent().getChildren()[MainView.VIEW_INDEX_APP])
			.getMainMenu().getLogInOut();
		final Event event = new Event();

		event.index = this.viewIndex;
		event.item = menuItem;
		event.text = menuItem.getText();
		event.time = (int) System.currentTimeMillis();
		event.doit = true;
		event.widget = menuItem;
		event.display = menuItem.getDisplay();
		event.type = SWT.MouseUp;
		menuItem.notifyListeners(SWT.MouseUp, event);
	}
	
	void setViewIndex(final int viewIndex) {
		if (viewIndex < 0 || viewIndex > MainMenuView.VIEW_INDEX_ABOUT) {
			// TODO: log issue

			return;
		}
		this.viewIndex = viewIndex;
	}
	
	private void clear() {
		this.username.setText("");
		this.password.setText("");
	}
	
	private boolean verifyLoggin() {
		// TODO: Add functionality to authenticate and authorise
		final boolean authorised = "user".equals(this.username.getText()) 
				&& "user".equals(this.password.getText());

		// Auto-logout on expiring authorisation/authentication
		final Thread thread = this.thread;

		if (thread != null) {
			thread.interrupt();
		}
		runLogoutTask();

		return authorised;
	}

	public void runLogoutTask() {
		this.thread = new Thread(new Runnable() {

			@Override
			public void run() {
				if (!LoginView.this.isDisposed() && LoginView.this.isLoggedIn()) {
					try {
						Thread.sleep(AUTH_TIME);
						if (!LoginView.this.isDisposed()) {
							LoginView.this.getDisplay().asyncExec(new Runnable() {
			
								@Override
							    public void run() {
									if (!LoginView.this.isDisposed()) {
					        	   		final MainMenuView menu = getMenuView();
					        	   		final MenuItem item = (MenuItem) menu
					        	   				.getComponent(MainMenuView.INDEX_MENU)
					        	   				.getChildren()[LoginView.this.viewIndex];

					        	   		if (item.needs2Login()) {
											LoginView.this.display(LoginView.this.viewIndex);
										} else {
											LoginView.this.loggedIn = false;
						       	   			menu.setLogInOut(MainMenuView.MENU_LOGIN);
										}
									}
							    }
	
							});
						}
					} catch (final InterruptedException ie) {
						// Nothing to do
					}
				}
				LoginView.this.thread = null;
			}

		}, "Auth-Timeout");
		this.thread.start();
	}
	
	private MainMenuView getMenuView() {
   		final MainView mainView = (MainView) getParent().getChildren()[MainView.VIEW_INDEX_APP];

   		return (MainMenuView) ((SashForm) mainView.getChildren()[MainView.VIEW_INDEX_MENU]).getChildren()[0];
	}

} // end class LoginView
