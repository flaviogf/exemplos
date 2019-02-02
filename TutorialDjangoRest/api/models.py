from django.db import models

# Create your models here.

LANGUAGE_CHOICES = [('1', 'language1'), ('2', 'language2')]
STYLE_CHOICES = [('1', 'style1'), ('2', 'style2')]


class Snippet(models.Model):
    created = models.DateTimeField(auto_now_add=True)
    title = models.CharField(max_length=100, blank=True, default='')
    code = models.TextField()
    lineos = models.BooleanField(default=False)
    language = models.CharField(choices=LANGUAGE_CHOICES,
                                default='language1',
                                max_length=100)
    style = models.CharField(choices=STYLE_CHOICES,
                             max_length=100,
                             default='style1')

    def __str__(self):
        return self.title

    class Meta:
        ordering = ('created',)
