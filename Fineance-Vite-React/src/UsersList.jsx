import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { useNavigate } from 'react-router-dom';

function UsersList() {
    const [users, setUsers] = useState([])
    const [loading, setLoading] = useState(true)

    useEffect(() => {
        const token = localStorage.getItem('token') // zakładamy, że został wcześniej zapisany po logowaniu

        fetch('http://localhost:8080/api/users', {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            }
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

    const handleLogout = () => {
        localStorage.removeItem('token');

        // Opcjonalnie: wyczyść stan użytkownika w aplikacji
        // setCurrentUser(null);

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
