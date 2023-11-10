import {baseURL} from "../util/SectorUtil";
import axios from "axios";

export const getSectorData = () => {
    return axios.get(baseURL);
}