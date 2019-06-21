from aioredis import create_connection, create_redis
from jinja2 import Environment, PackageLoader, select_autoescape
from sanic import Sanic
from sanic.response import json, html
from sanic.websocket import WebSocketProtocol

from chat_sanic.chat import init_chat
from chat_sanic.views import init_views

app = Sanic()

env = Environment(loader=PackageLoader('chat_sanic', 'templates'), autoescape=select_autoescape(['html', 'xml']))


def render_template(name, *args, **kwargs):
    template = env.get_template(name)
    template = template.render(*args, **kwargs)
    return html(template)


@app.listener('before_server_start')
async def setup_redis(app, loop):
    redis = await create_redis('redis://localhost', loop=loop)
    app.redis = redis


@app.get('/status')
def status(request):
    return json({'data': request.ip})


init_views(app=app, render_template=render_template)

init_chat(app=app)

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True, protocol=WebSocketProtocol)
