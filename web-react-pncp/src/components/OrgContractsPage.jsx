import React from 'react';
import OrgSearchForm from './OrgSearchForm';
import ContractsList from './ContractsList';
import { useOrgContracts } from '../hooks/useOrgContracts';

const OrgContractsPage = () => {
  const { contracts, totalValue, searchContracts } = useOrgContracts();

  const handleSearch = (cnpj, dataIni, dataFim) => {
    searchContracts(cnpj, dataIni, dataFim);
  };

  return (
    <div>
      <OrgSearchForm onSearch={handleSearch} />
      <ContractsList contracts={contracts} totalValue={totalValue} />
    </div>
  );
};

export default OrgContractsPage;
