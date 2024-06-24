import axios from "axios"
import { toast } from "react-toastify";

export const deleteTask = async (taskId) => {
    try {
        const response = await axios.delete(`/tasks/${taskId}`);

        if (response.status === 204) {
            toast.success("Tasks are deleted", {
                position:'top-right',
                closeOnClick:true
            });
            return true;
        } else if (response.status === 404) {
            console.error("Given task id does not exist");
        }
    } catch (e) {
        console.error("Task cannot be deleted: ", e);
        toast.error("Tasks cannot be deleted", {
            position:'top-right',
            closeOnClick:true
        });

        return false;
    }
}