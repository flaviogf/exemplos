from fastapi import FastAPI
from pydantic import BaseModel

app = FastAPI()


class Item(BaseModel):
    name: str
    description: str = None
    price: float
    tax: float


@app.get('/')
async def root():
    return {'message': 'Hello Fast API!'}


@app.get('/items/{item_id}')
def read_item(item_id):
    return {'item_id': item_id}


@app.post('/items')
def create_item(item: Item):
    return item
