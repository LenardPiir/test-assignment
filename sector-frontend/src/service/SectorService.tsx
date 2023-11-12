import {baseURL} from "../util/SectorUtil";
import axios from "axios";
import {toast} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';

export const getSectors = () => {
    return axios.get(baseURL + '/all');
}

export const saveSectors = (selectedSectors: string[]) => {
    return axios.post(baseURL + '/save', selectedSectors);
}

export const showToastMessage = () => {
    toast.success("Sectors have been saved.", {
        position: toast.POSITION.BOTTOM_CENTER,
    });
};