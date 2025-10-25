import React, { useState } from 'react'
import { NavLink, Navigate } from 'react-router-dom'
import { useAuth } from '../../Context/AuthContext'
import { productApi } from '../api/ProductApi'

function Signup() {
    const Auth = useAuth()
    const isLoggedIn = Auth.userIsAuthenticated()

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [name, setName] = useState('')
    const [email, setEmail] = useState('')
    const [isError, setIsError] = useState(false)
    const [errorMessage, setErrorMessage] = useState('')

    const handleInputChange = (e) => {
        const { name, value } = e.target
        if (name === 'username') {
            setUsername(value)
        } else if (name === 'password') {
            setPassword(value)
        } else if (name === 'name') {
            setName(value)
        } else if (name === 'email') {
            setEmail(value)
        }
    }

    const handleSubmit = async (e) => {
        e.preventDefault()

        if (!(username && password && name && email)) {
            setIsError(true)
            setErrorMessage('Please, inform all fields!')
            return
        }

        const user = { username, password, name, email }

        try {
            const response = await productApi.signup(user)
            const { id, name, role } = response.data
            const authdata = window.btoa(username + ':' + password)
            const authenticatedUser = { id, name, role, authdata }

            Auth.userLogin(authenticatedUser)

            setUsername('')
            setPassword('')
            setName('')
            setEmail('')
            setIsError(false)
            setErrorMessage('')
        } catch (error) {
            handleLogError(error)
            if (error.response && error.response.data) {
                const errorData = error.response.data
                let errorMessage = 'Invalid fields'
                if (errorData.status === 409) {
                    errorMessage = errorData.message
                } else if (errorData.status === 400) {
                    errorMessage = errorData.errors[0].defaultMessage
                }
                setIsError(true)
                setErrorMessage(errorMessage)
            }
        }
    }

    if (isLoggedIn) {
        return <Navigate to='/' />
    }

    return (
    <div className="container">
    <div className="center-container">
      <form className="row g-3 pt-5" onSubmit={handleSubmit}>
        <div className="col-12">
          <label className="form-label">
            <h6>Username</h6>
          </label>
          <input
            className="form-control"
            placeholder="Type your username"
            onChange={handleInputChange}
            value={username}
            name="username"
          />
        </div>
        <div className="col-12">
          <label className="form-label">
            <h6>Password</h6>
          </label>
          <input
            type="password"
            className="form-control"
            placeholder="Type your password"
            value={password}
            name="password"
            onChange={handleInputChange}
            id="password"
          />
        </div>
        <div className="col-12">
          <label className="form-label">
            <h6>Name</h6>
          </label>
          <input
            className="form-control"
            placeholder="Type your name"
            onChange={handleInputChange}
            value={name}
            name="name"
            id="name"
          />
        </div>

        <div className="col-12">
          <label className="form-label">
            <h6>Email</h6>
          </label>
          <input
            className="form-control"
            placeholder="Type your email"
            onChange={handleInputChange}
            value={email}
            name="email"
            // value={`${stockAlert}/${stockQuantity}`}
            id="email"
          />
        </div>
        <div className="col-12">
          <button
            type="submit"
            className="btn btn-primary"
          >
            Sign Up
          </button>
        </div>
      </form>
    </div>
    </div>
  );
}

export default Signup