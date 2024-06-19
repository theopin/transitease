import axios from 'axios'

const TRANSPORT_API_URL = process.env.REACT_APP_TRANSPORT_API_URL
const TRANSPORT_API_KEY = process.env.REACT_APP_TRANSPORT_API_KEY


const instance = axios.create({
  baseURL: TRANSPORT_API_URL,
  timeout: 5000
})

const makeServiceRequest = async (endpoint: string, params = {}) => {
    return await instance.get(endpoint, { 
        params,
        headers: { 
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS',
            'AccountKey': TRANSPORT_API_KEY, 
            'accept': 'application/json',
        },
    })
}


export const TransportApi = {
    makeServiceRequest
}