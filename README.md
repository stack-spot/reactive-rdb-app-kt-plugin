# reactive-rdb-app-kt-plugin

This plugin handles configuring access to relational databases with the correct reactive drivers.
Supported Database Management Systems:
- PostgreSQL
- MySQL
- MariaDB
- SQL Server
- Oracle

For MySQL and Oracle DBMSs the latest versions of their reactive drivers were not used (0.9.0 and 0.4.0 respectively, at the time this was written) because it uses the most recent version of R2DBC Specification (0.9.0) and Spring Data still uses the old one, based on 0.8.X specification.
