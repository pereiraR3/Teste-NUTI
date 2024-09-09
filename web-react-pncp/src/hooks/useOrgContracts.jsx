// Descrição da função "useOrgContracts"
// A função useOrgContracts é um hook personalizado em React que lida com a busca de contratos de uma organização (com base no CNPJ) 
// e realiza duas operações principais:
// 
// 1. Envia uma requisição para persistir os dados no banco de dados.
// 2. Faz uma segunda requisição para buscar os dados persistidos e calcula o valor total dos contratos encontrados.

import { useState } from 'react';
import axios from 'axios';

// Hook personalizado que busca contratos de um órgão (por CNPJ) e calcula o valor total
export const useOrgContracts = () => {
  // Estado que guarda a lista de contratos
  const [contracts, setContracts] = useState([]);
  
  // Estado que guarda os dados do órgão
  const [orgao, setOrgao] = useState(null);
  
  // Estado que guarda o valor total dos contratos
  const [totalValue, setTotalValue] = useState(0);

  // Função assíncrona que busca os contratos de um órgão com base no CNPJ e no período fornecido
  const searchContracts = async (cnpj, dataIni, dataFim) => {
    try {

      // Requisição POST para persistir os dados no banco de dados, passando os parâmetros CNPJ e datas
      const persistResponse = await axios.post(`https://backend-nuti.onrender.com/api/v1/pncp/orgao`, null, {
        params: {
            cnpj: cnpj,
            dataInicial: dataIni,
            dataFinal: dataFim
        }
      });

      // Verifica se a resposta de persistência foi bem-sucedida (status 200)
      if (persistResponse.status === 200) {
        // Se a persistência for bem-sucedida, faz uma segunda requisição para buscar os contratos persistidos
        const fetchResponse = await axios.get(`https://backend-nuti.onrender.com/orgao/findAllByCnpjWithContratos/${cnpj}`);
        const data = fetchResponse.data;

        // Verifica se há dados retornados
        if (data.length > 0) {
          const orgaoData = data[0]; // Pega o primeiro órgão encontrado

          // Atualiza o estado com os dados do órgão
          setOrgao(orgaoData.orgaoResponseDTO);
          
          // Atualiza o estado com a lista de contratos do órgão
          setContracts(orgaoData.contratoResponseDTOList);

          // Calcula o valor total dos contratos somando o 'valorInicial' de cada contrato
          const total = orgaoData.contratoResponseDTOList.reduce((acc, contract) => acc + contract.valorInicial, 0);
          
          // Atualiza o estado com o valor total calculado
          setTotalValue(total);
        } else {
          // Se não encontrar contratos, reseta os estados
          setOrgao(null);
          setContracts([]);
          setTotalValue(0);
        }

      } else {
        // Lança um erro se a resposta de persistência não for bem-sucedida (status diferente de 200)
        throw new Error('Erro ao persistir os dados. Verifique os dados e tente novamente.');
      }

    } catch (error) {
      // Captura qualquer erro que ocorra nas requisições e lança uma mensagem apropriada
      throw new Error(error.response?.data?.message || 'Erro ao buscar os contratos.');
    }
  };

  // Retorna os estados e a função de busca (searchContracts) para uso nos componentes
  return { contracts, orgao, totalValue, searchContracts };
};
