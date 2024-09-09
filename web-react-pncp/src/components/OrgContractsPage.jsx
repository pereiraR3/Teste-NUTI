import React from 'react'; // Importa React para criar o componente
import OrgSearchForm from './OrgSearchForm'; // Importa o componente de formulário de busca de órgão
import ContractsList from './ContractsList'; // Importa o componente de lista de contratos
import { useOrgContracts } from '../hooks/useOrgContracts'; // Importa o hook personalizado para buscar contratos

// Componente OrgContractsPage: Responsável por exibir o formulário de busca e a lista de contratos
const OrgContractsPage = () => {
  // Usa o hook personalizado useOrgContracts para acessar os contratos, valor total e a função de busca
  const { contracts, totalValue, searchContracts } = useOrgContracts();

  // Função chamada quando o formulário de busca é enviado
  const handleSearch = (cnpj, dataIni, dataFim) => {
    // Chama a função de busca de contratos, passando o CNPJ, data inicial e data final
    searchContracts(cnpj, dataIni, dataFim);
  };

  // Renderiza o componente com o formulário de busca e a lista de contratos
  return (
    <div>
      {/* Componente de formulário de busca de órgão */}
      <OrgSearchForm onSearch={handleSearch} />

      {/* Componente que exibe a lista de contratos e o valor total */}
      <ContractsList contracts={contracts} totalValue={totalValue} />
    </div>
  );
};

export default OrgContractsPage; // Exporta o componente para ser utilizado em outras partes da aplicação
