import axios from "axios";

const httpClient = axios.create({
    baseURL: 'http://localhost:8080/jobSeeker/',
    headers: {
        'Content-Type' : 'application/json',
    },
});

export default httpClient;