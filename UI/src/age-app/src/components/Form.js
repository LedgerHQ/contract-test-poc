import React from 'react';
import './Form.css'

const BASE_URL = "http://localhost:5000/"

const inputParsers = {
  number(input) {
    return parseFloat(input);
  },
};

class ShakingError extends React.Component {
	constructor() { super(); this.state = { key: 0 }; }

	componentWillReceiveProps() {
    // update key to remount the component to rerun the animation
  	this.setState({ key: ++this.state.key });
  }
  
  render() {
  	return <div key={this.state.key} className="bounce">{this.props.text}</div>;
  }
}

class MyForm extends React.Component {
  constructor() {
    super();
    this.state = {};
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleSubmit = async (event) => {
    event.preventDefault();
    if (!event.target.checkValidity()) {
    	this.setState({
        invalid: true,
        displayErrors: true,
      });
      return;
    }
    const form = event.target;
    const data = new FormData(form);
    const postForm = (body) => {
      return fetch(BASE_URL + 'user', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: stringifyFormData(data)
      });
    };

    for (let name of data.keys()) {
      const input = form.elements[name];
      const parserName = input.dataset.parse;
      if (parserName) {
        const parsedValue = inputParsers[parserName](data.get(name))
        data.set(name, parsedValue);
      }
    }
    this.setState({
    	res: stringifyFormData(data),
      invalid: false,
      displayErrors: false,
    });
    const res = await postForm(data)
    const dataToDisplay = await res.json()
    this.setState({
      res: dataToDisplay,
      invalid: false,
      displayErrors: false,
    })

  }

  render() {
  	const { res, invalid, displayErrors } = this.state;
    return (
    	<div className="login-box">
        <form
          onSubmit={this.handleSubmit}
          noValidate
          className={displayErrors ? 'displayErrors' : ''}
         >
        <div className="user-box">
          <label htmlFor="name"></label>
          <input
            id="name"
            name="name"
            type="text"
          />
        </div>
        <div className="user-box">
          <label htmlFor="age"></label>
          <input id="age" name="age" type="text" required />
        </div>
          <a href="#">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <button className="tn-anim4">Submit</button>
          </a>
        </form>
        
        <div className="res-block">
          {invalid && (
            <ShakingError text="Form is not valid" />
          )}
          {!invalid && res && (
          	<div className="response-block">
              <h3>Listen, {res.name}</h3>
              <pre>you are {res.age}</pre>
          	</div>
          )}
        </div>
    	</div>
    );
  }
}

function stringifyFormData(fd) {
  const data = {};
	for (let key of fd.keys()) {
  	data[key] = fd.get(key);
  }
  return JSON.stringify(data, null, 2);
}

export default MyForm;
