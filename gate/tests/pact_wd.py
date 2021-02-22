import atexit
import unittest
import os
import pytest

from pact import Consumer, Like, Provider, Term
from gate.src.gate import request_wd

PACT_MOCK_HOST = 'localhost'
PACT_MOCK_PORT = 8080
PACT_DIR = os.path.dirname(os.path.realpath(__file__))

pact = Consumer('Consumer').has_pact_with(Provider('Provider'))
pact.start_service()
atexit.register(pact.stop_service)

PACT_FILE = "user_age.json"

URL = f"http://{PACT_MOCK_HOST}:{PACT_MOCK_PORT}/user"

@pytest.fixture(scope='session')
def pact(request):
    pact = Consumer('gate').has_pact_with(
        Provider('wd'), host_name=PACT_MOCK_HOST, port=PACT_MOCK_PORT,
        pact_dir=PACT_DIR)
    pact.start_service()
    yield pact
    pact.stop_service()

    #version = request.config.getoption('--publish-pact')


def test_old_user(pact):
    expected = {
      'age': 'old',
      'name': 'Doe',
    }

    (pact
     .given('User Doe is 123 years old')
     .upon_receiving('a request for User Doe')
     .with_request(
        method='POST',
        path= '/user',
        body={'name': 'Doe', 'age': 123},
        headers={'Content-Type': 'application/json'})
     .will_respond_with(200, body=expected))

    with pact:
        result = request_wd('Doe', 123)
        assert('old' in result)



def test_young_user(pact):
    expected = {
      'age': 'young',
      'name': 'Smith',
    }

    (pact
     .given('User Smith is 10 years old')
     .upon_receiving('a request for User Smith')
     .with_request(
        method='POST',
        path= '/user',
        body={'name': 'Smith', 'age': 10},
        headers={'Content-Type': 'application/json'})
     .will_respond_with(200, body=expected))

    with pact:
        result = request_wd('Smith', 10)
        assert('young' in result)
