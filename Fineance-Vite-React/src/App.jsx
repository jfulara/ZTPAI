import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import UsersList from './views/UsersList'
import UserDetails from './views/UserDetails'
import ExpensesList from './views/ExpensesList'
import IncomesList from './views/IncomesList'
import Home from './views/Home'
import Login from './views/Login'
import Register from './views/Register'
import Messages from './views/Messages'
import AddIncome from './views/AddIncome'
import AddExpense from './views/AddExpense'
import OperationHistory from './views/OperationHistory'
import './styles/App.css'
import { AuthProvider } from './contexts/AuthContext'
import PrivateRoute from './routes/PrivateRoute';
import PublicRoute from './routes/PublicRoute';

function App() {
    return (
        <AuthProvider>
            <Router>
                <Routes>
                    <Route element={<PublicRoute />}>
                        <Route path="/login" element={<Login />} />
                        <Route path="/register" element={<Register />} />
                    </Route>
                    <Route element={<PrivateRoute />} >
                        <Route path="/" element={<Home />} />
                        <Route path="/addIncome" element={<AddIncome />} />
                        <Route path="/addExpense" element={<AddExpense />} />
                        <Route path="/history" element={<OperationHistory />} />
                        <Route path="/users" element={<UsersList />} />
                        <Route path="/users/:id_user" element={<UserDetails />} />
                        <Route path="/users/:id_user/operations/expenses" element={<ExpensesList />} />
                        <Route path="/users/:id_user/operations/incomes" element={<IncomesList />} />
                        <Route path="/messages" element={<Messages />} />
                    </Route>
                </Routes>
            </Router>
        </AuthProvider>
    )
}

export default App