import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'

function IncomesList() {
    const { id_user } = useParams()
    const [incomes, setIncomes] = useState([])
    const [loading, setLoading] = useState(true)

    useEffect(() => {
        const token = localStorage.getItem('token')

        fetch(`http://localhost:8080/api/users/${id_user}/operations/incomes`, {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            }
        })
            .then(res => res.json())
            .then(data => {
                setIncomes(data)
                setLoading(false)
            })
            .catch(err => {
                console.error('Błąd pobierania wpływów:', err)
                setLoading(false)
            })
    }, [incomes])

    return (
        <div>
            <h1>Lista wpływów</h1>
            {loading ? (
                <p>Ładowanie...</p>
            ) : (
                <ul>
                    {incomes.map(income => (
                        <li key={income.id_income}>
                            {income.title} - {income.amount} zł - {income.category} - {income.date}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    )
}

export default IncomesList
