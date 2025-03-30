import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'

function UsersList() {
    const [users, setUsers] = useState([])
    const [loading, setLoading] = useState(true)

    useEffect(() => {
        fetch('http://localhost:8080/api/users')
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

    return (
        <div>
            <h1>Lista użytkowników</h1>
            {loading ? (
                <p>Ładowanie...</p>
            ) : (
                <ul>
                    {users.map(user => (
                        <li key={user.id}>
                            <Link to={`/users/${user.id}`}>{user.email}</Link>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    )
}

export default UsersList
