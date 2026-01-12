from superset.models.core import Database
from superset.models.sql_lab import SavedQuery
from superset.extensions import db

db_obj = db.session.query(Database).filter_by(database_name='Test Database').first()
if not db_obj:
    print('Database not found')
    exit(1)

print(f'Database ID: {db_obj.id}')
print(f'Database name: {db_obj.database_name}')
