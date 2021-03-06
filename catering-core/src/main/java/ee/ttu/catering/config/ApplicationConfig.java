package ee.ttu.catering.config;

import ee.ttu.catering.config.authentication.UnauthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Configuration
@ComponentScan("ee.ttu.catering.rest")
public class ApplicationConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	public static UnauthenticationEntryPoint unauthenticationEntryPoint() {
		return new UnauthenticationEntryPoint();
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
	    return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {

		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
		
		InternalResourceViewResolver r1 = new InternalResourceViewResolver();
		r1.setPrefix("/WEB-INF/pages/");
		r1.setSuffix(".jsp");
		r1.setViewClass(JstlView.class);
		resolvers.add(r1);
		
		JsonViewResolver r2 = new JsonViewResolver();
		resolvers.add(r2);
		
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setViewResolvers(resolvers);
		resolver.setContentNegotiationManager(manager);
	    return resolver;
	}
	
	/**
	* View resolver for returning JSON in a view-based system. Always returns a
	* {@link MappingJacksonJsonView}.
	*/
	public class JsonViewResolver implements ViewResolver {
		public View resolveViewName(String viewName, Locale locale)
				throws Exception {
				MappingJackson2JsonView view = new MappingJackson2JsonView();
				view.setPrettyPrint(true);
				return view;
		}
	}
}
