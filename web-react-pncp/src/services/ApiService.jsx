import axios from 'axios';

export const saveContractsToDB = async (contracts) => {
    try {
        const response = await axios.post('/api/saveContracts', contracts, {
            headers: {
                'Content-Type': 'application/json',
            }
        });

        console.log('Contratos salvos com sucesso!', response.data);
    } catch (err) {
        console.error('Erro ao salvar contratos no banco de dados', err.response ? err.response.data : err.message);
    }
};
