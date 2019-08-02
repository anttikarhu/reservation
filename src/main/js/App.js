import React from 'react';
import ReactDOM from 'react-dom';

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
                    <h1>Henkilön 123456-1234 ajanvaraukset</h1>
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

ReactDOM.render(
    <App/>,
    document.getElementById('react')
);
