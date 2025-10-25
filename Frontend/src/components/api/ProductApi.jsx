import axios from 'axios'
import { config } from '../../Constants'

export const productApi = {
  authenticate,
  signup,
  /*getUsers,
  getUser,
  deleteUser,
  getProducts,
  getProduct,
  updateProduct,
  deleteProduct,
  addProduct*/
}

function authenticate(username, password) {
  return instance.post('/auth/authenticate', { username, password }, {
    headers: { 'Content-type': 'application/json' }
  })
}

function signup(user) {
  return instance.post('/auth/signup', user, {
    headers: { 'Content-type': 'application/json' }
  })
}

// -- Axios

const instance = axios.create({
  baseURL: config.url.API_BASE_URL
})

// -- Helper functions

function basicAuth(user) {
  return `Basic ${user.authdata}`
}