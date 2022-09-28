package uk.co.bocaditos.rwtwithspringboot.config;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.rap.rwt.client.WebClient;
import org.springframework.stereotype.Component;

import uk.co.bocaditos.rwtwithspringboot.entrypoints.MainRwtOnSpringBootEntryPoint;

import java.util.HashMap;


/**
 * The RWT ApplicationConfiguration is created as a managed Spring bean (component) to be able to 
 * benefit from DI and to be injected into other initialiser beans (see RwtContextInitializer).
 */
@Component
public class RwtApplicationConfiguration implements ApplicationConfiguration {

    @Override
    public void configure(final Application application) {
        // SWT_COMPATIBILITY mode is required for using blocking Dialog API. Required when using JFace
        application.setOperationMode(Application.OperationMode.SWT_COMPATIBILITY);

        // Register entrypoints, resources, themes, service handlers etc...
        final HashMap<String, String> properties = new HashMap<>();

        properties.put(WebClient.PAGE_TITLE, "Menu API");

        // RWT themes can be used as well
        application.addStyleSheet(RWT.DEFAULT_THEME_ID, "themes/customRwtTheme.css");

        addEntryPoints(application, properties);
    }
    
    private void addEntryPoints(final Application application, 
    		final HashMap<String, String> properties) {
        // Using a static reference to the EntryPoint class here. It could also be an 
    	// EntryPointFactory that works with injected EntryPoint components at application startup
        application.addEntryPoint("/" + MainRwtOnSpringBootEntryPoint.PATH, 
        		MainRwtOnSpringBootEntryPoint.class, properties);
    }

} // end class RwtApplicationConfiguration
