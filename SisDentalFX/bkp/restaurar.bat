set PGPASSWORD=postgres123
cd bkp
pg_restore.exe -c --host localhost --port 5432 --username "postgres" --dbname "sisdentaldb" --verbose --no-password "bkp.sql"