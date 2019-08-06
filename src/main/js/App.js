import { hot } from 'react-hot-loader/root';
import React from 'react';
import SomeModule from './SomeModule';
import SomeModule2 from './SomeModule2';

class App extends React.Component {
    constructor(props) {
        super(props);

        this.state = {appointments: []};
    }

    componentDidMount() {
        fetch('/api/fhir/Appointment?patient=123456-1234')
            .then(res => res.json())
            .then((data) => {
                this.setState({appointments: data.entry})
            })
            .catch(console.log)
    }

    render() {
        return (
            <div>
                <header>
                    <h1>Reservations for person 123456-1234</h1>
                    <h2><SomeModule /></h2>
                    <h2><SomeModule2 /></h2>
                </header>
                {this.state.appointments.map((a) => (
                    <div key={a.resource.id}>
                        <h2>{a.resource.resourceType} '{a.resource.description}'</h2>
                        <strong>{a.resource.status}</strong>
                        <p>{a.resource.start} - {a.resource.end}</p>
                    </div>
                ))}
            </div>)
    }
}

export default hot(App);