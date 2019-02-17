from django.urls import path

from api.views import SnippetList

urlpatterns = [
    path('snippets/', SnippetList.as_view())
]
