from django import forms

from contatos.models import Contato


class ContatoForm(forms.ModelForm):
    class Meta:
        model = Contato

        fields = '__all__'

        labels = {
            'nome': 'Nome',
            'email': 'E-mail',
            'telefone': 'Telefone',
        }

        error_messages = {
            'nome': {
                'required': 'nome obrigatório',
            },
            'email': {
                'required': 'e-mail obrigatório',
                'invalid': 'e-mail inválido'
            },
            'telefone': {
                'required': 'telefone obrigatório'
            },
        }
