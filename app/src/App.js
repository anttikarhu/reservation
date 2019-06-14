import React from 'react';
import {Button} from 'reactstrap';
import './App.css';

function App() {
    return (
        <div className="App">
            <header className="App-header">
                <p>Testing, testing, 1, 2, 3...</p>
                <Button color="success">Yes button</Button>
                <Button color="danger">No button</Button>
            </header>
        </div>
    );
}

export default App;
