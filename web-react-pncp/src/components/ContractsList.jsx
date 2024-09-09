import React, { useState } from 'react'; // Importa React e o hook useState para gerenciamento de estado
import OrgInfo from './OrgInfo'; // Importa o componente OrgInfo, que exibe informações sobre o órgão
import Pagination from './Pagination'; // Importa o componente de paginação

// Componente ContractsList: Exibe a lista de contratos de um órgão com paginação e expansão de texto
const ContractsList = ({ orgao, contracts, totalValue }) => {
  // Estado para armazenar a página atual
  const [currentPage, setCurrentPage] = useState(1);

  // Estado para controlar quais contratos têm o texto expandido
  const [expandedIndexes, setExpandedIndexes] = useState([]); 

  // Define quantos contratos serão exibidos por página
  const contractsPerPage = 5;

  // Calcula o índice do último e do primeiro contrato a ser exibido na página atual
  const indexOfLastContract = currentPage * contractsPerPage;
  const indexOfFirstContract = indexOfLastContract - contractsPerPage;

  // Obtém os contratos da página atual com base no índice
  const currentContracts = contracts.slice(indexOfFirstContract, indexOfLastContract);

  // Calcula o número total de páginas
  const totalPages = Math.ceil(contracts.length / contractsPerPage);

  // Função para trocar de página
  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  // Função para expandir ou colapsar o texto dos contratos
  const toggleExpand = (index) => {
    setExpandedIndexes((prev) =>
      prev.includes(index) ? prev.filter((i) => i !== index) : [...prev, index]
    );
  };

  return (
    // Contêiner principal da lista de contratos com borda, sombra e padding
    <div id="contractsList" className="relative overflow-x-auto shadow-md sm:rounded-lg mt-10 p-4 sm:p-6 bg-white border border-blue-300 rounded-lg shadow-lg">
      
      {/* Componente que exibe informações do órgão (como nome e CNPJ) */}
      <OrgInfo orgao={orgao} contractsCount={contracts.length} totalValue={totalValue} />

      {/* Cabeçalho da tabela de contratos */}
      <h2 className="text-lg sm:text-xl font-semibold mb-4 text-blue-700">Contratos Encontrados</h2>

      {/* Tabela que exibe os contratos */}
      <div className="overflow-x-auto">
        <table className="w-full max-w-[1024px] mx-auto text-sm text-left text-black-500 dark:text-black-400">
          <thead className="text-xs text-gray-700 uppercase bg-blue-50 dark:bg-black-700 dark:text-black-400">
            <tr>
              {/* Cabeçalhos das colunas da tabela */}
              <th scope="col" className="px-2 sm:px-6 py-3">Data de Vigência Início</th>
              <th scope="col" className="px-2 sm:px-6 py-3">Data de Vigência Fim</th>
              <th scope="col" className="px-2 sm:px-6 py-3">Razão Social do Fornecedor</th>
              <th scope="col" className="px-2 sm:px-6 py-3">Objeto do Contrato</th>
              <th scope="col" className="px-2 sm:px-6 py-3 text-blue-600">Valor Inicial do Contrato</th>
            </tr>
          </thead>
          
          {/* Corpo da tabela onde os contratos são listados */}
          <tbody>
            {currentContracts.map((contract, index) => (
              <tr
                key={index}
                className="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
              >
                {/* Data de início do contrato */}
                <td className="px-2 sm:px-6 py-4 font-medium text-black whitespace-nowrap dark:text-black">
                  {contract.dataVigenciaIni}
                </td>

                {/* Data de fim do contrato */}
                <td className="px-2 sm:px-6 py-4 text-black dark:text-black">
                  {contract.dataVigenciaFim}
                </td>

                {/* Razão social do fornecedor */}
                <td className="px-2 sm:px-6 py-4 text-black dark:text-black">
                  {contract.razaoSocialFornecedor}
                </td>

                {/* Objeto do contrato com função de expandir/colapsar */}
                <td className="px-2 sm:px-6 py-4 text-black dark:text-black">
                  {contract.objetoContrato.length > 50 && !expandedIndexes.includes(index) ? (
                    <>
                      {contract.objetoContrato.slice(0, 50)}...{' '}
                      <button
                        onClick={() => toggleExpand(index)} // Expande ou colapsa o texto ao clicar
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

                {/* Valor inicial do contrato formatado como moeda brasileira */}
                <td className="px-2 sm:px-6 py-4 text-blue-600 font-bold">
                  {contract.valorInicial.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* Exibe o valor total dos contratos */}
      <h2 className="text-lg sm:text-xl font-semibold mt-6 text-blue-600">
        Valor Total dos Contratos: {totalValue.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}
      </h2>

      {/* Componente de Paginação */}
      <Pagination totalPages={totalPages} currentPage={currentPage} paginate={paginate} />
    </div>
  );
};

export default ContractsList; // Exporta o componente para ser utilizado em outras partes da aplicação
