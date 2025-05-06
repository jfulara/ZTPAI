import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import UsersList from './UsersList'
import UserDetails from './UserDetails'
import ExpensesList from './ExpensesList'
import IncomesList from './IncomesList'
import Home from './Home'
import Login from './Login'
import './App.css'

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/users" element={<UsersList />} />
                <Route path="/users/:id_user" element={<UserDetails />} />
                <Route path="/users/:id_user/operations/expenses" element={<ExpensesList />} />
                <Route path="/users/:id_user/operations/incomes" element={<IncomesList />} />
            </Routes>
        </Router>
    )
}

export default App