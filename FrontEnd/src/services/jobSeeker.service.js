import httpClient from '../http-common';

const checkCredentials = (data) => {
    return httpClient.get('/jobSeeker/signIn',{ params: data });
};

const getjobs = ()=>{
    return httpClient.get('/jobSeeker/jobs');
};

export default { checkCredentials, getjobs };