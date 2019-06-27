from redis import Redis
from time import sleep

client = Redis()

p = client.pubsub()

p.subscribe('news')


while True:
    message = p.get_message()

    if not message:
        sleep(1)
        continue

    news = message['data']

    print(f'> news received {news}')

    client.rpush('news', news)
