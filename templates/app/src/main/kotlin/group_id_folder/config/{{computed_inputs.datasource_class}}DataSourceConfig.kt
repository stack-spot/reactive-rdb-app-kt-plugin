package {{project_group_id}}.config

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.core.R2dbcEntityOperations
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@Configuration
@EnableR2dbcRepositories(
    basePackages = ["{{project_group_id}}.{{repository_path}}"],
    entityOperationsRef = "{{inputs.datasource_name|to_lower_camel}}R2dbcEntityOperations"
)
class {{inputs.datasource_name|to_camel}}DataSourceConfig(
    @Value("\${{'{'}}{{inputs.datasource_name|to_kebab}}.datasource.url{{'}'}}") private val databaseUrl: String,
    @Value("\${{'{'}}{{inputs.datasource_name|to_kebab}}.datasource.username{{'}'}}") private val databaseUser: String,
    @Value("\${{'{'}}{{inputs.datasource_name|upper}}_DATASOURCE_PASSWORD{{'}'}}") private val databasePassword: String
) : AbstractR2dbcConfiguration() {

    @Bean(name = ["{{inputs.datasource_name|to_lower_camel}}ConnectionFactory"])
    override fun connectionFactory(): ConnectionFactory {
        return ConnectionFactories.get(
            ConnectionFactoryOptions
                .builder()
                .from(ConnectionFactoryOptions.parse(databaseUrl))
                .option(ConnectionFactoryOptions.USER, databaseUser)
                .option(ConnectionFactoryOptions.PASSWORD, databasePassword)
                .build()
        )
    }

    @Bean(name = ["{{inputs.datasource_name|to_lower_camel}}R2dbcEntityOperations"])
    fun r2dbcEntityOperations(@Qualifier("{{inputs.datasource_name|to_lower_camel}}ConnectionFactory") {{inputs.datasource_name|to_lower_camel}}ConnectionFactory: ConnectionFactory): R2dbcEntityOperations {
        return R2dbcEntityTemplate({{inputs.datasource_name|to_lower_camel}}ConnectionFactory)
    }
}
