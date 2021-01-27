/* eslint-disable no-console */
import * as axios from 'axios'
// import { getCookie } from "./cookie"

export default class Api {
  constructor() {
    this.api_token = null
    this.client = null
    this.api_url = process.env.REACT_APP_API_ENDPOINT
  }

  init = () => {
    this.api_token = process.env.REACT_APP_API_KEY // getCookie('ACCESS_TOKEN')
    // console.log(`-------${this.api_token}`)
    const headers = {
      Accept: 'application/json',
    }

    if (this.api_token) {
      headers.Authorization = `Bearer ${this.api_token}`
    }

    this.client = axios.create({
      baseURL: this.api_url,
      timeout: 31000,
      headers,
    })

    return this.client
  }

  getToc = (pathId) => {
    // console.log(`/toc/${pathId}`)
    return this.init().get(`/toc`, { pathId })
  }

  getToc = (pathId) => {
    // console.log(`/toc/${pathId}`)
    return this.init().get(`/toc`, { pathId })
  }

  getKnbContent = (pathId) => {
    // console.log(`/knbcontent/${pathId}`)
    return this.init().get(`/knbcontent`, { pathId })
  }

  getUserList = (params) => {
    return this.init().get('/users', { params })
  }

  addNewUser = (data) => {
    return this.init().post('/users', data)
  }
}
