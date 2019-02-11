from django.shortcuts import redirect, render
from django.views import View

from contatos.forms import ContatoForm
from contatos.models import Contato

# Create your views here.


class ContatoCreate(View):
    def get(self, request):
        form = ContatoForm()
        return render(request, 'contatos/create.html', {'form': form})

    def post(self, request):
        form = ContatoForm(request.POST or None)

        if form.is_valid():
            form.save()
            return redirect('contatos:list')

        return render(request, 'contatos/create.html', {'form': form})


class ContatoList(View):
    def get(self, request):
        contatos = Contato.objects.all()
        return render(request, 'contatos/index.html', {'contatos': contatos})


class ContatoUpdate(View):
    def get(self, request, contato_id):
        contato = Contato.objects.get(contato_id=contato_id)
        form = ContatoForm(instance=contato)
        return render(request, 'contatos/update.html', {'contato': contato, 'form': form})

    def post(self, request, contato_id):
        contato = Contato.objects.get(contato_id=contato_id)
        form = ContatoForm(request.POST, instance=contato)

        if form.is_valid():
            form.save()
            return redirect('contatos:list')

        return render(request, 'contatos/update.html', {'contato': contato, 'form': form})
