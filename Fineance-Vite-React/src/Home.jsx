import { Link } from 'react-router-dom'
import { useNavigate } from 'react-router-dom'

function Home() {
    const navigate = useNavigate();

    const handleLogout = () => {
        localStorage.removeItem('token');

        // Opcjonalnie: wyczyść stan użytkownika w aplikacji
        // setCurrentUser(null);

        navigate('/login');
    };

    return (
        <div>
            <h1>Witaj w aplikacji!</h1>
            <nav>
                <ul>
                    <li><Link to="/users">Lista użytkowników</Link></li>
                    <li><Link to="/messages">Wiadomości do RabbitMQ</Link></li>
                </ul>
            </nav>
            <button onClick={handleLogout}>
                Wyloguj się
            </button>
        </div>
    )
}

export default Home
