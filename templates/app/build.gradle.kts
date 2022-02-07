{%-
    set driverDict = {
        'postgresql': ['io.r2dbc:r2dbc-postgresql:0.8.11.RELEASE', 'org.postgresql:postgresql'],
        'mysql': ['dev.miku:r2dbc-mysql:0.8.2.RELEASE', 'mysql:mysql-connector-java'],
        'mariadb': ['org.mariadb:r2dbc-mariadb:1.0.3', 'org.mariadb.jdbc:mariadb-java-client'],
        'mssql': ['io.r2dbc:r2dbc-mssql:0.8.8.RELEASE', 'com.microsoft.sqlserver:mssql-jdbc'],
        'sqlserver': ['io.r2dbc:r2dbc-mssql:0.8.8.RELEASE', 'com.microsoft.sqlserver:mssql-jdbc'],
        'oracle': ['com.oracle.database.r2dbc:oracle-r2dbc:0.1.0', 'com.oracle.database.jdbc:ojdbc8']
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
    {% if computed_inputs.dbms in driverDict %}
    implementation("{{driverDict[computed_inputs.dbms][0]}}")
    runtimeOnly("{{driverDict[computed_inputs.dbms][1]}}")
    {% endif %}
    {% if inputs.migration_tool in migrationDict  %}
    runtimeOnly("{{migrationDict[inputs.migration_tool]}}")
    {% endif %}
}
