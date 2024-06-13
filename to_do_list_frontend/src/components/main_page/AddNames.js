import axios from "axios"

export const addName = async (name) => {
    if (!name || typeof name !== 'string' ||name.trim === '') {
        throw new Error("Wrong name");
    }

    try {
        const response = await axios.post('/names', { name });
        return response.data;
    } catch (error) {
        console.error("Name cannot be added: ", error);
    }
}