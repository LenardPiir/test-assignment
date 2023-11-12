import {baseURL} from "../util/SectorUtil";
import axios from "axios";
import {toast} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';

export const getSectorData = () => {
    return axios.get(baseURL);
}

export const showToastMessage = () => {
    toast.success("Sectors have been saved.", {
        position: toast.POSITION.BOTTOM_CENTER,
    });
};