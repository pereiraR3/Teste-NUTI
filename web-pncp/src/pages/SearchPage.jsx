import React from 'react';
import useSessionState from './useSessionState';

const SearchPage = () => {
    const [searchHistory, setSearchHistory] = useSessionState('searchHistory', []);

    const addSearch = (newSearch) => {
        setSearchHistory([...searchHistory, newSearch]);
    };

    return (
        <div>
            <h2>Histórico de Pesquisas</h2>
            <ul>
                {searchHistory.map((search, index) => (
                    <li key={index}>
                        <p>CNPJ: {search.cnpj}</p>
                        <p>Data Início: {search.dataIni}</p>
                        <p>Data Fim: {search.dataFim}</p>
                        <p>Valor Total: {search.valorTotal}</p>
                    </li>
                ))}
            </ul>
            <button onClick={() => addSearch({ cnpj: '12345678000195', dataIni: '2024-01-01', dataFim: '2024-08-01', valorTotal: 5000 })}>
                Adicionar Pesquisa Exemplo
            </button>
        </div>
    );
};

export default SearchPage;
