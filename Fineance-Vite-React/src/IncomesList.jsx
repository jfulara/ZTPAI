import { useEffect, useState } from 'react'

function IncomesList() {
    const [incomes, setIncomes] = useState([])
    const [loading, setLoading] = useState(true)

    useEffect(() => {
        fetch('http://localhost:8080/api/operations/incomes')
            .then(res => res.json())
            .then(data => {
                setIncomes(data)
                setLoading(false)
            })
            .catch(err => {
                console.error('Błąd pobierania wpływów:', err)
                setLoading(false)
            })
    }, [])

    return (
        <div>
            <h1>Lista wpływów</h1>
            {loading ? (
                <p>Ładowanie...</p>
            ) : (
                <ul>
                    {incomes.map(income => (
                        <li key={income.id}>
                            {income.title} - {income.amount} zł - {income.category} - {income.date}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    )
}

export default IncomesList
