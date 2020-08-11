package dev.ranieri.configs;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dev.ranieri.beans.Box;

@Component // This annotation is necessary for to read this class to help generate the IoC container
@Configuration // Configuration is a stereotype. Defines what this component is used for. 
//@Configuration is means this class will be used as definition for beans
public class AppConfig {

	@Bean //  This defines this method as creating a bean for the IoC container
	// In this case a lowValueBox with a value of 2;
	// by default all beans that you create are singletons
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public Box lowValueBox() {
		Box b =new Box(2);
		return b;
	}
	
	@Bean
	// a prototype bean will create a new instance of this bean whenever you request it from the IoC container
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE) 
	public Box highValueBox() {
		Box b =new Box(100);
		return b;
	}
}
