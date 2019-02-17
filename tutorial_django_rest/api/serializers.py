from rest_framework import serializers

from api.models import LANGUAGE_CHOICES, STYLE_CHOICES, Snippet


"""
class SnippetSerializer(serializers.Serializer):
    id = serializers.IntegerField(read_only=True)
    title = serializers.CharField(required=False,
                                  allow_blank=True,
                                  max_length=100)
    code = serializers.CharField(style={'base_template': 'textarea.html'})
    lineos = serializers.BooleanField(required=False)
    language = serializers.ChoiceField(choices=LANGUAGE_CHOICES,
                                       default='language1')
    style = serializers.ChoiceField(choices=STYLE_CHOICES,
                                    default='style1')

    def create(self, **data):
        return Snippet.objects.create(data)

    def update(self, obj, data):
        obj.title = data.get('title', data.title)
        obj.code = data.get('code', data.code)
        obj.lineos = data.get('lineos', data.lineos)
        obj.language = data.get('language', data.language)
        obj.style = data.get('style', data.style)
        obj.save()
        return obj
"""


class SnippetSerializer(serializers.ModelSerializer):
    class Meta:
        model = Snippet
        fields = ('id', 'title', 'code', 'lineos', 'language', 'style')
