"""Schemas for demoService"""
def serializer(item) -> dict:
    """Serializer for demoService"""
    return {
        "id": str(item["_id"]),
        "name": item["name"]
    }

def list_serializer(items) -> list:
    """List serializer for demoService"""
    return [serializer(item) for item in items]
