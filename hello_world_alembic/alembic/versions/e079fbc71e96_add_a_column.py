"""Add a column

Revision ID: e079fbc71e96
Revises: 840dc726dfbd
Create Date: 2019-07-11 09:08:22.050894

"""
from alembic import op
import sqlalchemy as sa


# revision identifiers, used by Alembic.
revision = 'e079fbc71e96'
down_revision = '840dc726dfbd'
branch_labels = None
depends_on = None


def upgrade():
    op.add_column('account', sa.Column('last_transaction_date', sa.DateTime))


def downgrade():
    op.drop_column('account', 'last_transaction_date')
