import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import PrivateRoute from './PrivateRoute';
import UsersList from './UsersList'
import UserDetails from './UserDetails'
import ExpensesList from './ExpensesList'
import IncomesList from './IncomesList'
import Home from './Home'
import Login from './Login'
import Register from './Register'
import Messages from './Messages'
import './App.css'

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route element={<PrivateRoute />} >
                    <Route path="/" element={<Home />} />
                    <Route path="/users" element={<UsersList />} />
                    <Route path="/users/:id_user" element={<UserDetails />} />
                    <Route path="/users/:id_user/operations/expenses" element={<ExpensesList />} />
                    <Route path="/users/:id_user/operations/incomes" element={<IncomesList />} />
                    <Route path="/messages" element={<Messages />} />
                </Route>
            </Routes>
        </Router>
    )
}

export default App