# reactive-rdb-app-kt-plugin

This plugin handles configuring access to relational databases with the correct reactive drivers.
Supported Database Management Systems:
- PostgreSQL
- MySQL
- MariaDB

The latest version of reactive driver was not used for the MySQL DBMS (0.9.0, at the time this was written) because it uses the most recent version of R2DBC Specification (0.9.0) and Spring Data still uses the old one, based on 0.8.X specification.

