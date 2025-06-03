import React, { useContext } from 'react'
import { Navigate, Outlet } from 'react-router-dom'
import { AuthContext } from '../contexts/AuthContext'

const AdminRoute = () => {
    const { user, loading } = useContext(AuthContext)

    if (loading) {
        return <p>Ładowanie...</p>
    }

    return user.role === "ADMIN" ? <Outlet /> : <Navigate to="/" />
}

export default AdminRoute
