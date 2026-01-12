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

from superset.connectors.sqla.models import SqlaTable

tbl = SqlaTable(
    table_name='test_users',
    schema='public',
    database=db_obj
)
db.session.add(tbl)
db.session.commit()
print(f'Table test_users created with ID: {tbl.id}')

tbl2 = SqlaTable(
    table_name='test_sales',
    schema='public',
    database=db_obj
)
db.session.add(tbl2)
db.session.commit()
print(f'Table test_sales created with ID: {tbl2.id}')

from superset.models.core import Dashboard

dash = Dashboard(
    dashboard_title='Test Dashboard',
    owners=[db.session.query(db.session.execute('SELECT id FROM ab_user WHERE username = :username', {'username': 'admin'}).fetchone()[0])]
)
db.session.add(dash)
db.session.commit()
print(f'Dashboard created with ID: {dash.id}')
