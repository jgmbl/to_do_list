export const fetchNames = async () => {
    try {
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