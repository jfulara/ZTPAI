import { useState } from 'react';
import { useNavigate } from 'react-router-dom'
import { AuthContext } from '../contexts/AuthContext'
import { useContext } from 'react'

function Login() {
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [error, setError] = useState('')
    const navigate = useNavigate()
    const { setUser } = useContext(AuthContext)

    const handleLogin = async (e) => {
        e.preventDefault()

        try {
            const response = await fetch('http://localhost:8080/api/auth/login', {
                method: 'POST',
                credentials: 'include',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ email, password })
            });

            if (response.ok) {
                const userData = await response.json();
                setUser(userData);
                navigate('/')
            } else {
                setError('Niepoprawne dane logowania')
            }
        } catch (error) {
            setError('Błąd logowania')
        }
    };

    return (
        <form onSubmit={handleLogin}>
            <h2>Logowanie</h2>
            <div>
                <label>Email:</label>
                <input value={email} onChange={(e) => setEmail(e.target.value)} />
            </div>
            <div>
                <label>Hasło:</label>
                <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
            </div>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <button type="submit">Zaloguj</button>
        </form>
    );
}

export default Login;