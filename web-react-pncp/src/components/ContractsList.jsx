import React, { useState } from 'react';
import OrgInfo from './OrgInfo';
import Pagination from './Pagination';

const ContractsList = ({ orgao, contracts, totalValue }) => {
  const [currentPage, setCurrentPage] = useState(1);
  const [expandedIndexes, setExpandedIndexes] = useState([]); // Estado para controlar a expansão dos textos
  const contractsPerPage = 5; // Número de contratos por página

  // Lógica de paginação
  const indexOfLastContract = currentPage * contractsPerPage;
  const indexOfFirstContract = indexOfLastContract - contractsPerPage;
  const currentContracts = contracts.slice(indexOfFirstContract, indexOfLastContract);

  const totalPages = Math.ceil(contracts.length / contractsPerPage);

  // Função para trocar de página
  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  // Função para expandir/colapsar texto
  const toggleExpand = (index) => {
    setExpandedIndexes((prev) =>
      prev.includes(index) ? prev.filter((i) => i !== index) : [...prev, index]
    );
  };

  return (
    <div id="contractsList" className="relative overflow-x-auto shadow-md sm:rounded-lg mt-10 p-4 sm:p-6 bg-white border border-blue-300 rounded-lg shadow-lg">
      {/* Informações do Órgão sempre visíveis */}
      <OrgInfo orgao={orgao} contractsCount={contracts.length} totalValue={totalValue} />

      {/* Lista de Contratos */}
      <h2 className="text-lg sm:text-xl font-semibold mb-4 text-blue-700">Contratos Encontrados</h2>
      <div className="overflow-x-auto">
        <table className="w-full max-w-[1024px] mx-auto text-sm text-left text-black-500 dark:text-black-400">
          <thead className="text-xs text-gray-700 uppercase bg-blue-50 dark:bg-black-700 dark:text-black-400">
            <tr>
              <th scope="col" className="px-2 sm:px-6 py-3">Data de Vigência Início</th>
              <th scope="col" className="px-2 sm:px-6 py-3">Data de Vigência Fim</th>
              <th scope="col" className="px-2 sm:px-6 py-3">Razão Social do Fornecedor</th>
              <th scope="col" className="px-2 sm:px-6 py-3">Objeto do Contrato</th>
              <th scope="col" className="px-2 sm:px-6 py-3 text-blue-600">Valor Inicial do Contrato</th>
            </tr>
          </thead>
          <tbody>
            {currentContracts.map((contract, index) => (
              <tr
                key={index}
                className="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
              >
                <td className="px-2 sm:px-6 py-4 font-medium text-black whitespace-nowrap dark:text-black">
                  {contract.dataVigenciaIni}
                </td>
                <td className="px-2 sm:px-6 py-4 text-black dark:text-black">
                  {contract.dataVigenciaFim}
                </td>
                <td className="px-2 sm:px-6 py-4 text-black dark:text-black">
                  {contract.razaoSocialFornecedor}
                </td>
                <td className="px-2 sm:px-6 py-4 text-black dark:text-black">
                  {contract.objetoContrato.length > 50 && !expandedIndexes.includes(index) ? (
                    <>
                      {contract.objetoContrato.slice(0, 50)}...{' '}
                      <button
                        onClick={() => toggleExpand(index)}
                        className="text-blue-600 underline"
                      >
                        Ver mais
                      </button>
                    </>
                  ) : (
                    <>
                      {contract.objetoContrato}{' '}
                      {contract.objetoContrato.length > 50 && (
                        <button
                          onClick={() => toggleExpand(index)}
                          className="text-blue-600 underline"
                        >
                          Ver menos
                        </button>
                      )}
                    </>
                  )}
                </td>
                <td className="px-2 sm:px-6 py-4 text-blue-600 font-bold">
                  {contract.valorInicial.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <h2 className="text-lg sm:text-xl font-semibold mt-6 text-blue-600">
        Valor Total dos Contratos: {totalValue.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}
      </h2>

      {/* Componente de Paginação */}
      <Pagination totalPages={totalPages} currentPage={currentPage} paginate={paginate} />
    </div>
  );
};

export default ContractsList;
