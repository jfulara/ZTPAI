import { Link, useNavigate } from 'react-router-dom'
import { AuthContext } from '../contexts/AuthContext'
import { useContext, useEffect, useState } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faGear, faChevronRight, faBars, faCirclePlus, faCircleMinus, faPlus } from '@fortawesome/free-solid-svg-icons'

function OperationHistory() {
    const navigate = useNavigate();
    const { logout, user } = useContext(AuthContext);
    const [operations, setOperations] = useState([]);
    const [titleFilter, setTitleFilter] = useState("");
    const [debouncedFilter, setDebouncedFilter] = useState(titleFilter);

    useEffect(() => {
        const handler = setTimeout(() => {
            setDebouncedFilter(titleFilter);
        }, 500);

        return () => {
            clearTimeout(handler);
        };
    }, [titleFilter]);

    useEffect(() => {
        const params = new URLSearchParams();
        params.append("id_user", user.id_user);
        if (titleFilter !== "") {
            params.append("title", debouncedFilter);
        }

        fetch(`http://localhost:8080/api/operations/history?${params.toString()}`, {
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'include'
        })
            .then(res => {
                if (!res.ok) throw new Error('Błąd serwera: ' + res.status);
                return res.json();
            })
            .then(data => setOperations(data))
            .catch(err => {
                console.error('Błąd pobierania danych:', err);
                navigate('/login');
            });
    }, [debouncedFilter]);

    const handleLogout = async () => {
        await logout();
        navigate('/login');
    };

    return (
        <>
            <nav className="menu">
                <div className="logo">
                    <p>Fineance</p>
                </div>
                <div className="welcome-string">
                    <p>Witaj {user.name}!</p>
                </div>
                <ul className="active">
                    <li><Link to="/" className="first">Podsumowanie<i className="fa-chevron-right"><FontAwesomeIcon icon={faChevronRight} /></i></Link></li>
                    <li><Link>Analiza budżetu<i className="fa-chevron-right"><FontAwesomeIcon icon={faChevronRight} /></i></Link></li>
                    <li><Link>Cele miesięczne<i className="fa-chevron-right"><FontAwesomeIcon icon={faChevronRight} /></i></Link></li>
                    <li><Link to="/history" className="active">Historia operacji<i className="fa-chevron-right"><FontAwesomeIcon icon={faChevronRight} /></i></Link></li>
                    <li><Link>Statystyki<i className="fa-chevron-right"><FontAwesomeIcon icon={faChevronRight} /></i></Link></li>
                    <li><Link>Oszczędzanie<i className="fa-chevron-right"><FontAwesomeIcon icon={faChevronRight} /></i></Link></li>
                </ul>
                <div className="buttons">
                    <button className="technical-help">Pomoc techniczna</button>
                    <button className="settings"><i className="fa-gear"><FontAwesomeIcon icon={faGear} /></i>Ustawienia</button>
                    <button className="logout" onClick={handleLogout}>Wyloguj się</button>
                </div>
                <ul className="mobile-icons">
                    <i className="fa-bars"><FontAwesomeIcon icon={faBars} /></i>
                </ul>
            </nav>
            <nav className="adder">
                <ul className="adder-list">
                    <li className="adder-item">
                        <Link to="/addIncome" className="adder-link">
                            <i className="fa-circle-plus"><FontAwesomeIcon icon={faCirclePlus} /></i>
                            <span className="link-text">&nbsp;&nbsp;&nbsp;&nbsp;Dodaj wpływ</span>
                        </Link>
                    </li>
                    <li className="adder-item">
                        <Link to="addExpense" className="adder-link">
                            <i className="fa-circle-minus"><FontAwesomeIcon icon={faCircleMinus} /></i>
                            <span className="link-text">&nbsp;&nbsp;&nbsp;&nbsp;Dodaj wydatek</span>
                        </Link>
                    </li>
                </ul>
                <div className="plus">
                    <i className="fa-plus"><FontAwesomeIcon icon={faPlus} /></i>
                </div>
            </nav>
            <main className="history-main">
                <div className="search-bar">
                    <input
                        type="text"
                        placeholder="Szukaj po tytule operacji..."
                        value={titleFilter}
                        onChange={e => setTitleFilter(e.target.value)}
                        style={{ width: '100%', padding: '8px', fontSize: '16px' }}
                    />
                </div>
                <div className="operations-list">
                    {operations.length === 0 ? (
                        <p>Brak operacji do wyświetlenia</p>
                    ) : (
                        <table>
                            <thead>
                            <tr>
                                <th>Data</th>
                                <th>Tytuł</th>
                                <th>Kategoria</th>
                                <th>Kwota</th>
                            </tr>
                            </thead>
                            <tbody>
                            {operations.map(op => (
                                <tr key={`${op.type}-${op.id}`}>
                                    <td>{new Date(op.date).toLocaleDateString()}</td>
                                    <td>{op.title}</td>
                                    <td>{op.category}</td>
                                    <td>{op.amount.toFixed(2)}</td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    )}
                </div>
            </main>
        </>
    );
}

export default OperationHistory;