import { useState } from 'react';
import axios from 'axios';

export const useOrgContracts = () => {
  const [contracts, setContracts] = useState([]);
  const [orgao, setOrgao] = useState(null);
  const [totalValue, setTotalValue] = useState(0);

  const searchContracts = async (cnpj, dataIni, dataFim) => {
    try {

      // Primeiro, faz a requisição para persistir os dados no banco de dados
      const persistResponse = await axios.post(`https://backend-nuti.onrender.com/api/v1/pncp/orgao`, {
        cnpj,
        dataInicial: dataIni,
        dataFinal: dataFim,
      });

      // Verifica se o status da resposta de persistência é 200 (sucesso)
      if (persistResponse.status === 200) {
        // Agora faz a requisição para buscar os dados persistidos

        const fetchResponse = await axios.get(`https://backend-nuti.onrender.com/orgao/findAllByCnpjWithContratos/${cnpj}`);
        const data = fetchResponse.data;

        if (data.length > 0) {
          const orgaoData = data[0];
          setOrgao(orgaoData.orgaoResponseDTO);
          setContracts(orgaoData.contratoResponseDTOList);

          // Calcula o valor total dos contratos
          const total = orgaoData.contratoResponseDTOList.reduce((acc, contract) => acc + contract.valorInicial, 0);
          setTotalValue(total);
        } else {
          // Se não encontrar dados, limpa os estados
          setOrgao(null);
          setContracts([]);
          setTotalValue(0);
        }
      } else {
        // Caso o status da resposta não seja 200, lança um erro para ser capturado
        throw new Error('Erro ao persistir os dados. Verifique os dados e tente novamente.');
      }

    } catch (error) {
      // Lança um erro caso qualquer das requisições falhe
      throw new Error(error.response?.data?.message || 'Erro ao buscar os contratos.');
    }
  };

  return { contracts, orgao, totalValue, searchContracts };
};
