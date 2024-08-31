import { useState, useEffect } from 'react';
import axios from 'axios';

const useFetchContracts = (cnpj, dataInicial, dataFinal, pages) => {
    const [contracts, setContracts] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [orgao, setOrgao] = useState(null);
    const [totalValue, setTotalValue] = useState(0);

    useEffect(() => {
        const fetchContracts = async () => {
            setLoading(true);
            try {
                const response = await axios.get('https://pncp.gov.br/api/consulta/v1/contratos', {
                    params: {
                        dataInicial,
                        dataFinal,
                        cnpjOrgao: cnpj,
                        pagina: pages
                    },
                    headers: {
                        'Accept': '*/*'
                    }
                });

                const data = response.data;

                // Calculando o valor total dos contratos
                const total = data.data.reduce((acc, contract) => acc + contract.valorInicial, 0);

                setContracts(data.data);
                setOrgao(data.data.length > 0 ? data.data[0].orgaoEntidade : null);
                setTotalValue(total);
            } catch (err) {
                setError(err.response ? err.response.data.message : err.message);
            } finally {
                setLoading(false);
            }
        };

        fetchContracts();
    }, [cnpj, dataInicial, dataFinal]);

    return { contracts, loading, error, orgao, totalValue };
};

export default useFetchContracts;
