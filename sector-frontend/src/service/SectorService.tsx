import {baseURL} from "../util/SectorUtil";
import axios from "axios";
import {toast} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import {SectorForm} from "../interface/SectorTypes";

export const getSectors = () => {
    return axios.get(baseURL + '/sectors');
}

export const saveForm = (sectorForm: SectorForm) => {
    return axios.post(baseURL + '/sectors', sectorForm);
}

export const showToastMessage = () => {
    toast.success("Sectors have been saved.", {
        position: toast.POSITION.BOTTOM_CENTER,
    });
};