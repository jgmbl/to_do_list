import axios from "axios";
import { toast } from "react-toastify"
import 'react-toastify/dist/ReactToastify.css';

export const addTask = async (id_name, content) => {
    if (!task || typeof task !== 'string' || task.trim === '') {
        toast.error("Enter a task", {
            position: 'top-right',
            closeOnClick:true
        });
        return;
    }


    const newTask = {
        "names": {
            "id": id_name,
        },
        "content": content,
    };

    try {
        const response = await axios.post('/task', { newTask });
    } catch (error) {
        console.error("Task cannot be added: ", error);
    }
}