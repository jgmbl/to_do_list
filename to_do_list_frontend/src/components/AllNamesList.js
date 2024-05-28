import { Component } from "react";
import { fetchNames } from "./Names";

class AllNamesList extends Component {
    state = {
        names: []
    };

    async componentDidMount() {
        const fetchedNames = await fetchNames();
        this.setState({ names: fetchedNames });
    }

    render() {
        const { names } = this.state;
        return (
            <div>
              <ul>
                {names.map((name, index) => (
                    <li key={index}>{name.name}</li>
                ))}
              </ul>
            </div>
        );
      }
}

export default AllNamesList;