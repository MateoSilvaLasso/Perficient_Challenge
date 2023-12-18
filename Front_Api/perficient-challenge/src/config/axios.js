import axios from 'axios'

const instance = axios.create({baseURL:"https://perficientchallenge-production.up.railway.app" })

localStorage.getItem('token') &&  (instance.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token') )

export default instance