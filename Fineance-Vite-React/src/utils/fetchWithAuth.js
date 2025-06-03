export async function fetchWithAuth(url, options = {}, navigate, logout) {
    let response = await fetch(url, { ...options });
    if (response.status !== 401) {
        return response;
    }

    const refreshResponse = await fetch('http://localhost:8080/api/auth/refresh', {
        method: 'POST',
        credentials: 'include',
    });
    if (refreshResponse.ok) {
        response = await fetch(url, { ...options });
        return response;
    } else {
        if (logout) await logout();
        if (navigate) navigate('/login');
        throw new Error('Sesja wygasła. Zaloguj się ponownie.');
    }
}

