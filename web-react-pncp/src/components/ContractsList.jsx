import React, { useState } from 'react';
import OrgInfo from './OrgInfo';
import Pagination from './Pagination';

const ContractsList = ({ orgao, contracts, totalValue }) => {
  const [currentPage, setCurrentPage] = useState(1);
  const contractsPerPage = 5; // Número de contratos por página

  // Lógica de paginação
  const indexOfLastContract = currentPage * contractsPerPage;
  const indexOfFirstContract = indexOfLastContract - contractsPerPage;
  const currentContracts = contracts.slice(indexOfFirstContract, indexOfLastContract);

  const totalPages = Math.ceil(contracts.length / contractsPerPage);

  // Função para trocar de página
  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  return (
    <div className="relative overflow-x-auto shadow-md sm:rounded-lg mt-10 p-4 sm:p-6 bg-white border border-blue-300 rounded-lg shadow-lg">
      {/* Informações do Órgão sempre visíveis */}
      <OrgInfo orgao={orgao} contractsCount={contracts.length} totalValue={totalValue} />

      {/* Lista de Contratos */}
      <h2 className="text-lg sm:text-xl font-semibold mb-4 text-blue-700">Contratos Encontrados</h2>
      <div className="overflow-x-auto">
        <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
          <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
            <tr>
              <th scope="col" className="px-2 sm:px-6 py-3">Data de Vigência Início</th>
              <th scope="col" className="px-2 sm:px-6 py-3">Data de Vigência Fim</th>
              <th scope="col" className="px-2 sm:px-6 py-3">Razão Social do Fornecedor</th>
              <th scope="col" className="px-2 sm:px-6 py-3">Objeto do Contrato</th>
              <th scope="col" className="px-2 sm:px-6 py-3">Valor Inicial do Contrato</th>
            </tr>
          </thead>
          <tbody>
            {currentContracts.map((contract, index) => (
              <tr
                key={index}
                className="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
              >
                <td className="px-2 sm:px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-gray-500">
                  {contract.dataVigenciaIni}
                </td>
                <td className="px-2 sm:px-6 py-4">{contract.dataVigenciaFim}</td>
                <td className="px-2 sm:px-6 py-4">{contract.razaoSocialFornecedor}</td>
                <td className="px-2 sm:px-6 py-4">{contract.objetoContrato}</td>
                <td className="px-2 sm:px-6 py-4">
                  {contract.valorInicial.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <h2 className="text-lg sm:text-xl font-semibold mt-6 text-blue-700">
        Valor Total dos Contratos: {totalValue.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}
      </h2>

      {/* Componente de Paginação */}
      <Pagination totalPages={totalPages} currentPage={currentPage} paginate={paginate} />
    </div>
  );
};

export default ContractsList;
