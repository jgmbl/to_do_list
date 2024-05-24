import { Component } from 'react';
import './App.css';
import AddNewTask from './components/AddNewTask';
import Appbar from './components/Appbar';

class App extends Component {
    render() {
        return (
            <div className="App">
                <Appbar/>
                <AddNewTask/>
            </div>
          );
    }
}

export default App;
