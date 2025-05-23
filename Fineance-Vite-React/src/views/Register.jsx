import { useState } from "react";
import { useNavigate } from 'react-router-dom'
import { Link } from 'react-router-dom'
import '../styles/style.css';
import '../styles/security.css';


function Register() {
    const [form, setForm] = useState({ name: "", surname: "", email: "", password: "", confirmPassword: "" });
    const [message, setMessage] = useState("")
    const navigate = useNavigate()

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch("http://localhost:8080/api/auth/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(form),
            });

            if (response.ok) {
                setMessage("Zarejestrowano pomyślnie!");
                navigate('/login')
            } else {
                const errorData = await response.json();
                setMessage(errorData.message || "Błąd rejestracji.");
            }
        } catch (error) {
            setMessage("Wystąpił błąd połączenia z serwerem.");
        }
    };

    return (
        <main>
            <div className="login-container">
                <div className="logo-side">
                    <div className="logo">
                        <p>Fineance</p>
                    </div>
                    <div className="description">
                        <p className="additional-description">Utwórz konto:</p>
                    </div>
                    <div className="login-link">
                        <p>Masz już konto?&nbsp;</p>
                        <Link to="/login">Przejdź do logowania!</Link>
                    </div>
                </div>
                <div className="form-side">
                    <form className="register" onSubmit={handleSubmit}>
                        <div className="messages">
                            {message && <p>{message}</p>}
                        </div>
                        <input name="name" placeholder="Imię" value={form.name} onChange={handleChange} required />
                        <input name="surname" placeholder="Nazwisko" value={form.surname} onChange={handleChange} required />
                        <input name="email" type="email" placeholder="Email" value={form.email} onChange={handleChange} required />
                        <input name="password" type="password" placeholder="Hasło" value={form.password} onChange={handleChange} required />
                        <input name="confirmPassword" type="password" placeholder="Powtórz hasło" value={form.confirmPassword} onChange={handleChange} required
                        />
                        <div className="button-background">
                            <button className="special-button" type="submit">Zarejestruj się</button>
                        </div>
                    </form>
                </div>
            </div>
        </main>
    );
}

export default Register;