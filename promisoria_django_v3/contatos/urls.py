from django.urls import path

from contatos.views import ContatoCreate, ContatoDelete, ContatoList, ContatoUpdate

app_name = 'contatos'

urlpatterns = [
    path('', ContatoList.as_view(), name='list'),
    path('criar/', ContatoCreate.as_view(), name='create'),
    path('<uuid:contato_id>/atualizar/', ContatoUpdate.as_view(), name='update'),
    path('<uuid:contato_id>/deletar/', ContatoDelete.as_view(), name='delete')
]
