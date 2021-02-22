# gate (Provider and Consumer)

## Run

```
python3 gate.py
```

## Generate Pact File (as the Consumer)

```
python -m pytest tests/pact_wd.py
```

## Issues

On macOS Big Sur:

- Had to uninstall Python that had been installed via Brew
- Install Python 3.9 from installer
- `/Applications/Python\ 3.9/Install\ Certificates.command`

Then:

- `python -m pip install -r requirements.txt`

To run the test (to generate the contract)

- `python -m pytest tests/pact_wd.py`