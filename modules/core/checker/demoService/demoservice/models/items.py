"""Import Modules"""
from pydantic import BaseModel

# Models
class Item(BaseModel):
    """Item model"""
    id: int
    name: str
    