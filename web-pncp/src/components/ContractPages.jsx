import React from 'react';
import useFetchContracts from './hooks/useFetchContracts';

const ContractsPage = () => {
    const cnpj = "33004540000100";  // Exemplo de CNPJ
    const dataInicial = "20240101"; // Exemplo de data inicial
    const dataFinal = "20240831";   // Exemplo de data final
    const pages = 1;

    const { contracts, loading, error, orgao, totalValue } = useFetchContracts(cnpj, dataInicial, dataFinal, pages);

    if (loading) return <p>Carregando...</p>;
    if (error) return <p>Erro: {error}</p>;

    return (
        <div>
            
            {orgao && (
                <div>
                    <h2>Informações do Órgão</h2>
                    <p><strong>CNPJ:</strong> {orgao.cnpj}</p>
                    <p><strong>Razão Social:</strong> {orgao.razaoSocial}</p>
                </div>
            )}

            <h2>Contratos</h2>
            {contracts.length > 0 ? (
                contracts.map(contract => (
                    <div key={contract.numeroControlePncpCompra}>
                        <p><strong>Data de Vigência Inicial:</strong> {contract.dataVigenciaInicio}</p>
                        <p><strong>Data de Vigência Final:</strong> {contract.dataVigenciaFim}</p>
                        <p><strong>Razão Social do Fornecedor:</strong> {contract.nomeRazaoSocialFornecedor}</p>
                        <p><strong>Objeto do Contrato:</strong> {contract.objetoContrato}</p>
                        <p><strong>Valor Inicial:</strong> R$ {contract.valorInicial.toLocaleString()}</p>
                        <hr />
                    </div>
                ))
            ) : (
                <p>Nenhum contrato encontrado.</p>
            )}

            <h2>Valor Total dos Contratos</h2>
            <p>R$ {totalValue.toLocaleString()}</p>
        </div>
    );
};

export default ContractsPage;
