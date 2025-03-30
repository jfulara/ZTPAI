import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'

function UserDetails() {
    const { id } = useParams()
    const [user, setUser] = useState(null)
    const [loading, setLoading] = useState(true)

    useEffect(() => {
        fetch(`http://localhost:8080/api/users/${id}`)
            .then(res => res.json())
            .then(data => {
                setUser(data)
                setLoading(false)
            })
            .catch(err => {
                console.error('Błąd pobierania użytkownika:', err)
                setLoading(false)
            })
    }, [id])

    if (loading) return <p>Ładowanie...</p>
    if (!user) return <p>Nie znaleziono użytkownika</p>

    return (
        <div>
            <h1>{user.name} {user.surname}</h1>
            <p>Email: {user.email}</p>
        </div>
    )
}

export default UserDetails
