import axios from 'axios'

const BACKEND_URL = process.env.REACT_APP_BACKEND_URL


const instance = axios.create({
  baseURL: BACKEND_URL,
  timeout: 5000
})

const makeServiceRequest = async (endpoint: string, params = {}) => {
    return await instance.get(endpoint, { params })
}


export const TransportApi = {
    makeServiceRequest
}