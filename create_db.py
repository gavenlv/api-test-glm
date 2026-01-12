from superset.models.core import Database
from superset.extensions import db

db_obj = Database(
    database_name='Test Database',
    sqlalchemy_uri='postgresql://superset:superset@db:5432/superset',
    expose_in_sqllab=True,
    allow_ctas=True,
    allow_dml=True
)
db.session.add(db_obj)
db.session.commit()
print(f'Database created with ID: {db_obj.id}')
