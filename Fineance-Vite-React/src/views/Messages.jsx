import { useState } from "react";

function Messages() {
    const [message, setMessage] = useState("");
    const [response, setResponse] = useState("");

    const handleSend = async () => {
        try {
            const res = await fetch("http://localhost:8080/api/messages", {
                method: "POST",
                headers: {
                    "Content-Type": "text/plain"
                },
                credentials: "include",
                body: message
            });

            const text = await res.text();
            setResponse(text);
        } catch (err) {
            setResponse("Błąd wysyłania wiadomości.");
            console.error(err);
        }
    };

    return (
        <div>
            <h2>Wyślij wiadomość do RabbitMQ</h2>
            <textarea
                rows="3"
                cols="40"
                value={message}
                onChange={(e) => setMessage(e.target.value)}
                placeholder="Wpisz wiadomość"
            />
            <br />
            <button onClick={handleSend}>Wyślij</button>
            {response && <p>{response}</p>}
        </div>
    );
}

export default Messages;
