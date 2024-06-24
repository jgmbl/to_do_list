import { Component } from 'react';
import './App.css';
import AddNameAndTask from './components/main_page/AddNameAndTask';
import ListOfTasks from './components/main_page/ListOfTasks';
import Appbar from './components/Appbar';

class App extends Component {
    render() {
        return (
            <div className="App">
                <Appbar/>
                <AddNameAndTask/>
                <ListOfTasks/>
            </div>
          );
    }
}

export default App;
