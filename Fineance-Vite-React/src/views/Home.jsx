import { Link } from 'react-router-dom'
import { useNavigate } from 'react-router-dom'
import { AuthContext } from '../contexts/AuthContext'
import { useContext } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faGear, faChevronRight, faBars, faCirclePlus, faCircleMinus, faPlus } from '@fortawesome/free-solid-svg-icons'

function Home() {
    const navigate = useNavigate();
    const { logout, user } = useContext(AuthContext);

    useEffect(() => {
        fetch('http://localhost:8080/api/home', {
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'include'
        })
            .then(res => {
                if (!res.ok) {
                    throw new Error('Nieautoryzowany dostęp');
                }
                return res.json();
            })
            .catch(err => {
                console.error('Błąd pobierania danych:', err);
                navigate('/login');
            });
    })

    const handleLogout = async () => {
        await logout();
        navigate('/login');
    };

    return (
        <>
            <nav className="menu">
                <div className="logo">
                    <p>
                        Fineance
                    </p>
                </div>
                <div className="welcome-string">
                    <p>
                        Witaj {user.name}!
                    </p>
                </div>
                <ul className="active">
                    <li><a className="first active">Podsumowanie<i className="fa-chevron-right"><FontAwesomeIcon icon={faChevronRight} /></i></a></li>
                    <li><a>Analiza budżetu<i className="fa-chevron-right"><FontAwesomeIcon icon={faChevronRight} /></i></a></li>
                    <li><a>Cele miesięczne<i className="fa-chevron-right"><FontAwesomeIcon icon={faChevronRight} /></i></a></li>
                    <li><a href="operations">Historia operacji<i className="fa-chevron-right"><FontAwesomeIcon icon={faChevronRight} /></i></a></li>
                    <li><a>Statystyki<i className="fa-chevron-right"><FontAwesomeIcon icon={faChevronRight} /></i></a></li>
                    <li><a>Oszczędzanie<i className="fa-chevron-right"><FontAwesomeIcon icon={faChevronRight} /></i></a></li>
                </ul>
                <div className="buttons">
                    <button className="technical-help">
                        Pomoc techniczna
                    </button>
                    <button className="settings">
                        <i className="fa-gear"><FontAwesomeIcon icon={faGear} /></i>Ustawienia
                    </button>
                    <button className="logout" onClick={handleLogout}>
                        Wyloguj się
                    </button>
                </div>
                <ul className="mobile-icons">
                    <i className="fa-bars"><FontAwesomeIcon icon={faBars} /></i>
                </ul>
            </nav>
            <nav className="adder">
                <ul className="adder-list">
                    <li className="adder-item">
                        <a href="addIncome" className="adder-link">
                            <i className="fa-circle-plus"><FontAwesomeIcon icon={faCirclePlus} /></i>
                            <span className="link-text">&nbsp;&nbsp;&nbsp;&nbsp;Dodaj wpływ</span>
                        </a>
                    </li>
                    <li className="adder-item">
                        <a href="addExpense" className="adder-link">
                            <i className="fa-circle-minus"><FontAwesomeIcon icon={faCircleMinus} /></i>
                            <span className="link-text">&nbsp;&nbsp;&nbsp;&nbsp;Dodaj wydatek</span>
                        </a>
                    </li>
                </ul>
                <div className="plus">
                    <i className="fa-plus"><FontAwesomeIcon icon={faPlus} /></i>
                </div>
            </nav>
            <main>
                <section className="top">
                    <h1>Podsumowanie miesiąca</h1>
                </section>
                <section className="bottom">
                    <section className="left-side">
                        <div className="comparisons">
                            <div className="income-to-expense">
                                <h1>Twój stosunek wpływów do wydatków w tym miesiącu to:</h1>
                                <h2> zł</h2>
                            </div>
                        </div>
                    </section>
                    <section className="right-side">
                        <div className="incomes-window">
                            <h1>Wpływy:  zł</h1>
                            <ul>

                            </ul>
                        </div>
                        <div className="expenses-window">
                            <h1>Wydatki:  zł</h1>
                            <ul>

                            </ul>
                        </div>
                    </section>
                </section>
            </main>
        </>
    )
}

export default Home
