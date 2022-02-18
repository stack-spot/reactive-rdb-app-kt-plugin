{%-
    set driverDict = {
        'PostgreSQL': ['io.r2dbc:r2dbc-postgresql:0.8.11.RELEASE', 'org.postgresql:postgresql'],
        'MySQL': ['dev.miku:r2dbc-mysql:0.8.2.RELEASE', 'mysql:mysql-connector-java'],
        'MariaDB': ['org.mariadb:r2dbc-mariadb:1.0.3', 'org.mariadb.jdbc:mariadb-java-client']
    }
-%}
{%-
    set migrationDict = {
        'Flyway': 'org.flywaydb:flyway-core',
        'Liquibase': 'org.liquibase:liquibase-core'
    }
-%}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("{{driverDict[inputs.dbms][0]}}")
    runtimeOnly("{{driverDict[inputs.dbms][1]}}")
    {% if inputs.migration_tool in migrationDict  %}
    runtimeOnly("{{migrationDict[inputs.migration_tool]}}")
    {% endif %}
}
