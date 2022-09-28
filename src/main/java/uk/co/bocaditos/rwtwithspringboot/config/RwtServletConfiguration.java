package uk.co.bocaditos.rwtwithspringboot.config;

import org.eclipse.rap.rwt.engine.RWTServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import uk.co.bocaditos.rwtwithspringboot.entrypoints.MainRwtOnSpringBootEntryPoint;


@Configuration
public class RwtServletConfiguration {

    /**
     * Registers the RWTServlet to the URLs of the entrypoints. Multiple URL mappings for multiple 
     * entrypoints can be added. Eliminates the need for a web.xml, together with the 
     * RwtContextInitializer component.
     */
    @Bean
    public ServletRegistrationBean<RWTServlet> rwtServlet() {
        final var bean = new ServletRegistrationBean<>(new RWTServlet());

        bean.addUrlMappings("/" + MainRwtOnSpringBootEntryPoint.PATH);

        return bean;
    }

} // end class RwtServletConfiguration
