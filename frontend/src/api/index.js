import axios from 'axios'
import nookies from 'nookies'
import { Base64 } from 'js-base64';

const kafkazApi = axios.create({
    baseURL: 'http://localhost:8080'
})

kafkazApi.interceptors.request.use(
    config => {
        const username = process.env.USERNAME || "user";
        const password = process.env.PASSWORD || "password";
        const credential = Base64.encode(`${username}:${password}`)
        config.headers.Authorization = `Bearer ${nookies.get({}).accessToken || credential}`
        return config
    },
    error => {
        console.log('error axios!')
        return Promise.reject(error)
    }
)

export default kafkazApi