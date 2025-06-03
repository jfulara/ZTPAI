import React, { useEffect } from "react";

function Docs() {
    useEffect(() => {
        window.location.href = "http://localhost:8080/api/docs";
    }, []);
    return null;
}

export default Docs;

