set PGUSER=postgres
set PGPASSWORD=postgres123

cd bkp 
pg_dump.exe --host localhost --port 5432 --format custom --blobs --verbose --file "bkp.sql" "sisdentaldb"
