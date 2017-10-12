package pl.j2dev.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages="pl.j2dev.configuration", 
	excludeFilters={
			@Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)
	})
@PropertySource("classpath:db.properties")
public class RootConfig {
	
	@Value("${db.url}")
    private String DBURL;
	
	@Value("${db.user}")
    private String DBUSER;
	
	@Value("${db.pass}")
    private String DBPASS;
	
    @Bean
	public DataSource getDataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
		dataSource.setUrl(DBURL);
		dataSource.setUsername(DBUSER);
		dataSource.setPassword(DBPASS);
		return dataSource;
	}
    
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
}
