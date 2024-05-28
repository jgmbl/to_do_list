import { Component } from "react";
import { fetchNames } from "./Names";

// Component - operations on Component like componentDidMount, update state of component etc.
class AllNamesList extends Component {
    state = {
        // set empty state at the begining
        names: []
    };

    // add method after mounting the component (add to React Tree)
    async componentDidMount() {
        // add async function and wait for the result (await) - download data from server
        // fetchNames -> Names.js
        const fetchedNames = await fetchNames();
        // update names to downloaded data from server
        this.setState({ names: fetchedNames });
    }

    // render is always called when state changes
    render() {
        // download names from state
        const { names } = this.state;
        return (
            <div>
              <ul>
                {names.map((name, index) => ( // iterate on names table 
                    <li key={index}>{name.name}</li>
                ))}
              </ul>
            </div>
        );
      }
}

export default AllNamesList;