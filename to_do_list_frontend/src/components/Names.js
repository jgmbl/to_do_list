// download data from server

//declare and export function fetchNames
export const fetchNames = async () => {
    try {
        // await - wait until http is completed
        // http request to /names endpoint
        const response = await fetch('/names');
        if (response.ok) {
            const names = await response.json();
            return names;
        } else {
            console.error("Names cannot be uploaded.");
            return [];
        }
    } catch (error) {
        console.error("Names cannot be uploaded: ", error);
        return [];
    }
};