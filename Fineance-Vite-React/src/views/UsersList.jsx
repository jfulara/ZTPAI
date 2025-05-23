import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../contexts/AuthContext'
import { useContext } from 'react'

function UsersList() {
    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(true);
    const { logout } = useContext(AuthContext);

    useEffect(() => {
        fetch('http://localhost:8080/api/users', {
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'include'
        })
            .then(res => res.json())
            .then(data => {
                setUsers(data)
                setLoading(false)
            })
            .catch(err => {
                console.error('Błąd pobierania użytkowników:', err)
                setLoading(false)
            })
    }, [])


    const navigate = useNavigate();

    const handleLogout = async () => {
        await logout();
        navigate('/login');
    };

    return (
        <div>
            <h1>Lista użytkowników</h1>
            {loading ? (
                <p>Ładowanie...</p>
            ) : (
                <ul>
                    {users.map(user => (
                        <li key={user.id_user}>
                            <Link to={`/users/${user.id_user}`}>{user.email}</Link>
                        </li>
                    ))}
                </ul>
            )}
            <button onClick={handleLogout}>
                Wyloguj się
            </button>
        </div>
    )
}

export default UsersList
