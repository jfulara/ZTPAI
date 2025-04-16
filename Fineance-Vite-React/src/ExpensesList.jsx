import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'

function ExpensesList() {
    const { id_user } = useParams()
    const [expenses, setExpenses] = useState([])
    const [loading, setLoading] = useState(true)

    useEffect(() => {
        fetch(`http://localhost:8080/api/users/${id_user}/operations/expenses`)
            .then(res => res.json())
            .then(data => {
                setExpenses(data)
                setLoading(false)
            })
            .catch(err => {
                console.error('Błąd pobierania wydatków:', err)
                setLoading(false)
            })
    }, [expenses])

    return (
        <div>
            <h1>Lista wydatków</h1>
            {loading ? (
                <p>Ładowanie...</p>
            ) : (
                <ul>
                    {expenses.map(expense => (
                        <li key={expense.id_expense}>
                            {expense.title} - {expense.amount} zł - {expense.category} - {expense.date}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    )
}

export default ExpensesList
