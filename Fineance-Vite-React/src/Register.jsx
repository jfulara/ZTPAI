import { useState } from "react";
import { useNavigate } from 'react-router-dom'

function Register() {
    const [form, setForm] = useState({ name: "", surname: "", email: "", password: "" })
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
        <form onSubmit={handleSubmit}>
            <input name="name" placeholder="Imię" value={form.name} onChange={handleChange} required/>
            <input name="surname" placeholder="Nazwisko" value={form.surname} onChange={handleChange} required/>
            <input name="email" type="email" placeholder="Email" value={form.email} onChange={handleChange} required/>
            <input name="password" type="password" placeholder="Hasło" value={form.password} onChange={handleChange} required/>
            <button type="submit">Zarejestruj się</button>
            {message && <p>{message}</p>}
        </form>
    );
}

export default Register;