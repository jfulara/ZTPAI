import { useState } from 'react';
import { useNavigate } from 'react-router-dom'

function Login() {
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [error, setError] = useState('')
    const navigate = useNavigate()

    const handleLogin = async (e) => {
        e.preventDefault()

        try {
            const response = await fetch('http://localhost:8080/api/auth/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ email, password })
            });

            if (response.ok) {
                const data = await response.json();
                localStorage.setItem('token', data.token);
                // Przekieruj użytkownika lub zaktualizuj stan aplikacji
                navigate('/users')
            } else {
                throw new Error('Błąd logowania')
            }
        } catch (error) {
            setError('Niepoprawne dane logowania')
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