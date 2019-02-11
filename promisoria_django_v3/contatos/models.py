import uuid

from django.db import models

# Create your models here.


class Contato(models.Model):
    contato_id = models.UUIDField(primary_key=True,
                                  default=uuid.uuid4,
                                  editable=False)

    nome = models.CharField(max_length=100)

    email = models.EmailField()

    telefone = models.CharField(max_length=11)
