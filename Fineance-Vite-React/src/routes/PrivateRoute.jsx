import React, { useContext } from 'react'
import { Navigate, Outlet } from 'react-router-dom'
import { AuthContext } from '../contexts/AuthContext'

const PrivateRoute = () => {
    const { user, loading } = useContext(AuthContext)

    if (loading) {
        return <p>Ładowanie...</p> // lub spinner
    }

    return user ? <Outlet /> : <Navigate to="/login" />
}

export default PrivateRoute
