import axios from "axios"
import { toast } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';

export const addName = async (name) => {
    if (!name || typeof name !== 'string' || name.trim === '') {
        toast.error("Select or enter a name", {
            position: 'top-right',
            closeOnClick: true
        });
        return;
    }

    try {
        const response = await axios.post('/names', { name });
        return response.data;
    } catch (error) {
        console.error("Name cannot be added: ", error);
    }
}