import React from 'react'; // Importa o React para criar o componente

// Componente OrgInfo: Exibe as informações de um órgão e o resumo dos contratos
const OrgInfo = ({ orgao, contractsCount, totalValue }) => {
  return (
    // Contêiner com estilos para layout e design visual
    <div className="mb-8 p-6 bg-blue-50 border border-blue-300 rounded-lg shadow-md">
      {/* Título da seção de informações do órgão */}
      <h2 className="text-xl font-semibold text-blue-700 mb-4">Informações do Órgão</h2>

      {/* Exibe as informações do órgão, incluindo razão social, CNPJ, unidade, UF e município */}
      <p><strong>Nome do Órgão:</strong> {orgao.razaoSocial}</p>
      <p><strong>CNPJ:</strong> {orgao.cnpj}</p>
      <p><strong>Nome da Unidade:</strong> {orgao.nomeUnidade}</p>
      <p><strong>Código da Unidade:</strong> {orgao.codigoUnidade}</p>
      <p><strong>Nome da UF:</strong> {orgao.ufNome}</p>
      <p><strong>Sigla da UF:</strong> {orgao.ufSigla}</p>
      <p><strong>Nome do Município:</strong> {orgao.municipioNome}</p>
      <p><strong>Código IBGE:</strong> {orgao.codigoIbge}</p>

      {/* Exibe a quantidade total de contratos e o valor total dos contratos */}
      <p><strong>Quantidade de Contratos:</strong> {contractsCount}</p>
      <p><strong>Valor Total dos Contratos:</strong> 
        {/* Formata o valor total dos contratos no formato de moeda brasileira */}
        {totalValue.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}
      </p>
    </div>
  );
};

export default OrgInfo; // Exporta o componente para ser utilizado em outras partes da aplicação
