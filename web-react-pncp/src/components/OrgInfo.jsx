import React from 'react';

const OrgInfo = ({ orgao, contractsCount, totalValue }) => {
  return (
    <div className="mb-8 p-6 bg-blue-50 border border-blue-300 rounded-lg shadow-md">
      <h2 className="text-xl font-semibold text-blue-700 mb-4">Informações do Órgão</h2>
      <p><strong>Nome do Órgão:</strong> {orgao.razaoSocial}</p>
      <p><strong>CNPJ:</strong> {orgao.cnpj}</p>
      <p><strong>Nome da Unidade:</strong> {orgao.nomeUnidade}</p>
      <p><strong>Código da Unidade:</strong> {orgao.codigoUnidade}</p>
      <p><strong>Nome da UF:</strong> {orgao.ufNome}</p>
      <p><strong>Sigla da UF:</strong> {orgao.ufSigla}</p>
      <p><strong>Nome do Município:</strong> {orgao.municipioNome}</p>
      <p><strong>Código IBGE:</strong> {orgao.codigoIbge}</p>
      <p><strong>Quantidade de Contratos:</strong> {contractsCount}</p>
      <p><strong>Valor Total dos Contratos:</strong> {totalValue.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}</p>
    </div>
  );
};

export default OrgInfo;
