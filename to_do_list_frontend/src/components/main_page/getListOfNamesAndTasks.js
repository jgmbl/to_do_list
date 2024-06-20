import axios from "axios"
import { toast } from "react-toastify";

export const getListOfNamesAndTasks = async () => {
    try {
        const response = await axios.get('/tasks');
        
        if (!response.data || response.data.length === 0) {
            toast.info("Add task to see the list", {
                position:'top-right',
                closeOnClick:true
            })
            return;
        }

        return response.data;
    } catch (e) {
        console.error("Tasks cannot be uploaded: ", e);
    }
}