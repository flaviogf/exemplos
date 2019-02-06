from flask import Flask
from flask_admin import Admin
from flask_admin.contrib.sqla import ModelView
from flask_marshmallow import Marshmallow
from flask_restful import Api, Resource
from flask_sqlalchemy import SQLAlchemy

admin = Admin(name="Agenda Flask 001", template_mode='bootstrap3')
db = SQLAlchemy()
ma = Marshmallow()


def create_app():
    from contatos import Contato, bp as contatos_bp

    app = Flask(__name__)

    app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///sqlite.db'
    app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
    app.secret_key = 'teste123'

    db.init_app(app)
    ma.init_app(app)
    admin.init_app(app)

    admin.add_view(ModelView(Contato, db.session))

    app.register_blueprint(contatos_bp)

    return app
