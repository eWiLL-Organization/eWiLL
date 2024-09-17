"""Import Modules"""
from pymongo.mongo_client import MongoClient

# adjust for dev and build environments
# client = MongoClient("mongodb://mongoadmin:mongoadmin@0.0.0.0:27017")
client = MongoClient("mongodb://mongoadmin:mongoadmin@localhost:27018")

try:
    client.admin.command("ping")
    print("Connected successfully!!!")
except Exception as e: # pylint: disable=broad-except
    print("Could not connect to MongoDB:", e)

db = client.assessment_db
collection = db["assessment_collection"]
