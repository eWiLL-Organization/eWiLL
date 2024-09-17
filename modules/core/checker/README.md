# Init Projekt

Python lokal installieren -> https://www.python.org/

Install Poetry locally -> pip install poetry

Allow virtualenvs -> poetry config virtualenvs.in-project true

Create new Projekt ->mkdir demoService

Navigate to first folder -> cd ../demoService

init Poetry -> poetry init

Starte ein virtuelles enviroment -> poetry shell
Stoppe das virtuelles enviroment -> exit
Bei Problemen mit der PowerShell die CommandPrompt verwenden!

Add Dependency ->
poetry add fastapi
poetry add "uvicorn[standard]"
demoService>poetry add gunicorn

Add files in ../demoService/demoservice/main.py

Füge den code hinzu um den server über "poetry run dev" zu starten
[tool.poetry.scripts]
dev = "uvicorn demoservice.main:app --reload"

Starte FastAPI -> poetry run uvicorn main:app --reload

Zugriff -> http://127.0.0.1:8000
Doku -> http://127.0.0.1:8000/docs

# Initialising a pre-existing project

Python lokal installieren -> https://www.python.org/

Install Poetry locally -> pip install poetry

Allow virtualenvs -> poetry config virtualenvs.in-project true

Navigiere in Projektordner -> cd ../xyz

Starte ein virtuelles enviroment -> poetry shell
Stoppe das virtuelles enviroment -> exit
Bei Problemen mit der PowerShell die CommandPrompt verwenden!

Installiere alle Dependencies -> poetry install

Starte FastAPI -> cd ./demoservice
-> uvicorn main:app --reload

Zugriff -> http://127.0.0.1:8000
Doku -> http://127.0.0.1:8000/docs

# Allgemein

Pylint 10/10 -> pylint demoservice

FastAPI Tutorial -> https://www.youtube.com/watch?v=KXCvIV3yr7c
FastAPI MongoDB Tutorial ->https://www.youtube.com/watch?v=QkGqjPFIGCA
