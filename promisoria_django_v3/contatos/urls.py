from django.urls import path

from contatos.views import ContatoCreate, ContatoList, ContatoUpdate

app_name = 'contatos'

urlpatterns = [
    path('', ContatoList.as_view(), name='list'),
    path('novo/', ContatoCreate.as_view(), name='create'),
    path('<uuid:contato_id>/atualiza/', ContatoUpdate.as_view(), name='update'),
]
