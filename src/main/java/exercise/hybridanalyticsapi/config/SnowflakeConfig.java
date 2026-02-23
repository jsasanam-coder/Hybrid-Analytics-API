package exercise.hybridanalyticsapi.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class SnowflakeConfig {

    @Value("${snowflake.url}")
    private String url;

    @Value("${snowflake.username}")
    private String username;

    @Value("${snowflake.password}")
    private String password;

    @Value("${snowflake.warehouse}")
    private String warehouse;

    @Value("${snowflake.database}")
    private String database;

    @Value("${snowflake.schema}")
    private String schema;

    @Value("${snowflake.jdbc-query-result-format:json}")
    private String jdbcQueryResultFormat;

    @Bean(name = "snowflakeDataSource")
    public DataSource snowflakeDataSource(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaximumPoolSize(5);
        dataSource.setMinimumIdle(1);
        dataSource.setConnectionTimeout(10000);
        dataSource.setIdleTimeout(10000);
        dataSource.setMaxLifetime(1800000);
        dataSource.setInitializationFailTimeout(10000);
        dataSource.setLeakDetectionThreshold(10000);

        Properties properties = new Properties();
        if(warehouse != null) properties.put("warehouse", warehouse);
        if(database != null) properties.put("database", database);
        if(schema != null) properties.put("schema", schema);
        if(jdbcQueryResultFormat != null) properties.put("jdbcQueryResultFormat", jdbcQueryResultFormat);
        dataSource.setDataSourceProperties(properties);
        return dataSource;
    }


}
