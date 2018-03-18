package br.com.claudiobs.transacao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

public class DataSourceConfig {

    @Bean
    public DataSource inMemoryDatasource() {
        return new EmbeddedDatabaseBuilder()
                .setType(H2)
                .continueOnError(false)
                .generateUniqueName(true)
                .addScript("/database/schema.sql")
                .build();
    }

}
