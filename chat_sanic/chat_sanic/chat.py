import asyncio

from sanic.response import json


def init_chat(**kwargs):
    app = kwargs.get('app')

    @app.post('/chat')
    def chat(request):
        app.redis.rpush('message', request.json['message'])
        return json({'data': 'OK'})

    @app.websocket('/chat')
    async def chat(request, websocket):
        while True:
            message = await app.redis.rpop('message', encoding='utf-8')

            if not message:
                await asyncio.sleep(0.5)
                continue

            await websocket.send(message)
