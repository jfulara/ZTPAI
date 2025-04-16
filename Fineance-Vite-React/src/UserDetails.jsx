import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { Link } from 'react-router-dom'

function UserDetails() {
    const { id_user } = useParams()
    const [user, setUser] = useState(null)
    const [loading, setLoading] = useState(true)

    useEffect(() => {
        fetch(`http://localhost:8080/api/users/${id_user}`)
            .then(res => res.json())
            .then(data => {
                setUser(data)
                setLoading(false)
            })
            .catch(err => {
                console.error('Błąd pobierania użytkownika:', err)
                setLoading(false)
            })
    }, [id_user])

    if (loading) return <p>Ładowanie...</p>
    if (!user) return <p>Nie znaleziono użytkownika</p>

    return (
        <div>
            <h1>{user.name} {user.surname}</h1>
            <p>Email: {user.email}</p>
            <p>
                <Link to={`/users/${user.id_user}/operations/expenses`}>Wydatki</Link>
            </p>
            <p>
                <Link to={`/users/${user.id_user}/operations/incomes`}>Wpływy</Link>
            </p>
        </div>
    )
}

export default UserDetails
