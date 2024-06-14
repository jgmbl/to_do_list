import axios from "axios"

export const getIdByName = async (name) => {
    try {
        const response = await axios.get(`/names/name/${name}`);
        if (response.status === 200) {
            return response.data;
        } else if (response.status === 404) {
            console.error(`Name ${name} not found in database.`);
        } else {
            console.error(`Unexpected response status: ${response.status}`);
        }
    } catch (e) {
        console.log("Id cannot be uploaded: ", e);
    }
}