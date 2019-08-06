import React from 'react';

class SomeModule extends React.Component {
    constructor(props) {
        super(props);

        this.state = {someData: Math.random()};
    }

    render() {
        return (
            <div>
                <p>This should be the same: {this.state.someData}</p>
            </div>)
    }
}

export default SomeModule;