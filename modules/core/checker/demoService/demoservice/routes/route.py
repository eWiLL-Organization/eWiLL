"""Demo service for checker"""
from fastapi import APIRouter
from models.items import Item
from config.database import collection
from schema.schemas import list_serializer
# from bson import ObjectId

router = APIRouter()

@router.get("/")
async def get_items():
    """Test service for checker"""
    items = list_serializer(collection.find())
    return items

@router.post("/")
async def add_item(item: Item):
    """Test service for checker"""
    collection.insert_one(dict(item))

# put
# delete
# find()
