FROM python:3.11-slim AS poetry

RUN pip3 install poetry

WORKDIR /appService
COPY modules/core/checker/demoService/pyproject.toml .
RUN poetry export -f requirements.txt --output requirements.txt

RUN pip install -r requirements.txt

COPY modules/core/checker/demoService/demoservice .

CMD ["gunicorn", "-w", "4", "-k", "uvicorn.workers.UvicornWorker", "main:app", "--bind", "0.0.0.0:80"]
# CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "80"]
