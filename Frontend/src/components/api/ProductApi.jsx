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
  getProducts,
  getProduct,
  searchProducts,
  getImage,
  deleteProduct,
  addProduct
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

function getProducts(user) {
  return instance.get('api/products', {
    headers: { 'Authorization': basicAuth(user) }
  })
}

function getProduct(user, id) {
  return instance.get(`api/product/${id}`, {
    headers: { 'Authorization': basicAuth(user) }
  })
}

function searchProducts(user, searchText) {
  return instance.get(`api/products/search?keyword=${searchText}`, {
    headers: { 'Authorization': basicAuth(user) }
  })
}

function deleteProduct(user, id) {
  return instance.delete(`api/product/${id}`, {
    headers: { 'Authorization': basicAuth(user) }
  })
}

function addProduct(user, formData) {
  return instance.post("api/product", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
          'Authorization': basicAuth(user)
        },
      })
}

function getImage(user, productId) {
  return instance.get(`api/product/${productId}/image`, {
      responseType: "blob", headers: { 'Authorization': basicAuth(user) }
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