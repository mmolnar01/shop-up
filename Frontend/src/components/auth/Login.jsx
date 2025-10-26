import React, { useState } from 'react'
import { NavLink, Navigate } from 'react-router-dom'
import { useAuth } from '../../Context/AuthContext'
import { productApi } from '../api/ProductApi'

function Login() {
    const Auth = useAuth()
    const isLoggedIn = Auth.userIsAuthenticated()

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [isError, setIsError] = useState(false)

    const handleInputChange = (e) => {
        const { name, value } = e.target
        if (name === 'username') {
            setUsername(value)
        } else if (name === 'password') {
            setPassword(value)
        }
    }

    const handleSubmit = async (e) => {
        e.preventDefault()

        if (!(username && password)) {
            setIsError(true)
            return
        }

        try {
            const response = await productApi.authenticate(username, password)
            const { id, name, role } = response.data
            const authdata = window.btoa(username + ':' + password)
            const authenticatedUser = { id, name, role, authdata }

            Auth.userLogin(authenticatedUser)

            setUsername('')
            setPassword('')
            setIsError(false)
        } catch (error) {
            handleLogError(error)
            setIsError(true)
        }
    }

    if (isLoggedIn) {
        return <Navigate to={'/'} />
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
                        <button
                            type="submit"
                            className="btn btn-primary"
                        >
                            Log In
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default Login