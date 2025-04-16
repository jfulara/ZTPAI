import { Link } from 'react-router-dom'

function Home() {
    return (
        <div>
            <h1>Witaj w aplikacji!</h1>
            <nav>
                <ul>
                    <li><Link to="/users">Lista użytkowników</Link></li>
                </ul>
            </nav>
        </div>
    )
}

export default Home
