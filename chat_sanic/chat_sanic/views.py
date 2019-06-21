def init_views(**kwargs):
    app = kwargs.get('app')
    render_template = kwargs.get('render_template')

    @app.get('/')
    def index(request):
        return render_template('index.html')
