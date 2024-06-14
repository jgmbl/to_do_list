import axios from "axios";
import { toast } from "react-toastify"
import 'react-toastify/dist/ReactToastify.css';
import { getIdByName } from "./GetIdByName";

export const addTask = async (names, content) => {
    if (!content || typeof content !== 'string' || content.trim() === '') {
        toast.error("Enter a task", {
            position: 'top-right',
            closeOnClick:true
        });
        return;
    }

    const id = await getIdByName(names);

    const newTask = {
        names: {
            id
        },
        content
    };

    try {
        const response = await axios.post('/tasks', newTask);
        console.log("Task is added to database: ", response.data);
        return response.data;
    } catch (error) {
        toast.error("Task cannot be added: " + error.message, {
            position: 'top-right',
            closeOnClick: true
        });
        console.error("Task cannot be added: ", error);
    }
}