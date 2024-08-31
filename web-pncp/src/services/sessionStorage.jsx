import React, { useState, useEffect } from 'react';

const useSessionState = (key, initialValue) => {
    const [state, setState] = useState(() => {
        const storedValue = sessionStorage.getItem(key);
        return storedValue ? JSON.parse(storedValue) : initialValue;
    });

    useEffect(() => {
        sessionStorage.setItem(key, JSON.stringify(state));
    }, [key, state]);

    return [state, setState];
};

export default useSessionState;
