import { useContext } from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { AuthContext } from '../contexts/AuthContext';

function PublicRoute() {
    const { user, loading } = useContext(AuthContext);

    if (loading) return null; // lub spinner

    return user ? <Navigate to="/" replace /> : <Outlet />;
}

export default PublicRoute;
