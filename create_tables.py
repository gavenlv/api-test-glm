from superset.models.core import Database
from superset.connectors.sqla.models import SqlaTable
from superset.extensions import db

db_obj = db.session.query(Database).filter_by(database_name='Test Database').first()
if not db_obj:
    print('Database not found')
    exit(1)

print(f'Database ID: {db_obj.id}')
print(f'Database name: {db_obj.database_name}')

table = SqlaTable(
    table_name='test_users',
    schema='public',
    database=db_obj
)
db.session.add(table)
db.session.commit()
print(f'Table created with ID: {table.id}')

table2 = SqlaTable(
    table_name='test_sales',
    schema='public',
    database=db_obj
)
db.session.add(table2)
db.session.commit()
print(f'Table2 created with ID: {table2.id}')
