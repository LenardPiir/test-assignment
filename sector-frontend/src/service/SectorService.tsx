import axios from "axios";
import {createContext, useEffect, useState} from "react";

const baseURL = '/sectors'

const SectorContext = createContext(null);

export default function SectorService(): void {
    const [sectors, setSectors] = useState(null);

    const getSectors = () => {
        axios
            .get(baseURL)
            .then(data => setSectors(data.data))
            .catch(error => console.log(error));
    };
}