from flask import Blueprint, request
from flask_marshmallow import Marshmallow
from flask_restful import Api, Resource

from app import db, ma


class Contato(db.Model):
    contato_id = db.Column(db.Integer, primary_key=True)
    nome = db.Column(db.String(50), nullable=False)


class ContatoSchema(ma.Schema):
    class Meta:
        model = Contato
        fields = ('contato_id', 'nome',)


class ContatosResource(Resource):
    def get(self):
        contatos = Contato.query.all()
        contatos = ContatoSchema(many=True).dump(contatos)
        return contatos

    def post(self):
        contato = request.json
        contato = ContatoSchema().load(contato)
        contato = Contato(**contato.data)
        db.session.add(contato)
        db.session.commit()
        contato = ContatoSchema().dump(contato)
        return contato


bp = Blueprint('contatos', __name__)
_api = Api(bp)
_api.add_resource(ContatosResource, '/api/v1/contatos')
