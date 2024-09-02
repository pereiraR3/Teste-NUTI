import { useState } from 'react';
import axios from 'axios';

export const useOrgContractsTest = () => {
  const [contracts, setContracts] = useState([]);
  const [orgao, setOrgao] = useState(null);
  const [totalValue, setTotalValue] = useState(0);

  const searchContracts = async (cnpj) => {
    try {

      const apiURL = process.env.REACT_APP_API_URL;

      const response = await axios.get(`https://backend-nuti.onrender.com/orgao/findAllByCnpjWithContratos/${cnpj}`);
      const data = response.data;

      if (data.length > 0) {
        const orgao = data[0]; // A lista retornada contém o órgão e seus contratos.
        setOrgao(orgao.orgaoResponseDTO);
        setContracts(orgao.contratoResponseDTOList);

        // Calcula o valor total dos contratos
        const total = orgao.contratoResponseDTOList.reduce((acc, contract) => acc + contract.valorInicial, 0);
        setTotalValue(total);
      } else {
        setOrgao(null);
        setContracts([]);
        setTotalValue(0);
      }

    } catch (error) {
      throw new Error('Erro ao buscar os contratos');
    }
  };

  return { contracts, orgao, totalValue, searchContracts };
};
