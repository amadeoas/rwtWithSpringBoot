package uk.co.bocaditos.rwtwithspringboot.entrypoints;

import java.io.InputStream;

import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import uk.co.bocaditos.rwtwithspringboot.views.LoginView;
import uk.co.bocaditos.rwtwithspringboot.views.MainView;


/**
 * Example RWT EntryPoint that is associated with the RWT application in 
 * config.RwtApplicationConfiguration.
 */
@SuppressWarnings("serial")
public class MainRwtOnSpringBootEntryPoint extends AbstractEntryPoint {
	
	public static final String PATH = "main";


	@Override
    protected void createContents(final Composite parent) {
		parent.setLayout(new StackLayout());

		if (parent instanceof Shell) {
			final InputStream stream = getClass().getResourceAsStream("/api.png");

			((Shell) parent).setImage(new Image(parent.getDisplay(), stream));
		}
		new LoginView(parent);
		new MainView(parent);

		MainView.setVisible(parent, MainView.VIEW_INDEX_APP);
    }

} // end class MainRwtOnSpringBootEntryPoint
