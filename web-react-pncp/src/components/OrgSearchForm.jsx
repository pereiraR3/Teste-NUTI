import React, { useState } from 'react';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import '../styles/variables.css';
import WarningModal from './WarningModal';

const OrgSearchForm = ({ onSearch, setIsTestLocal }) => {  // Recebe a função setIsTestLocal como prop
  const [cnpj, setCnpj] = useState('');
  const [dataIni, setDataIni] = useState('');
  const [dataFim, setDataFim] = useState('');
  const [isTestLocal, setIsTestLocalState] = useState(false);
  const [isWarningModalOpen, setIsWarningModalOpen] = useState(false);

  const handleCNPJInput = (e) => {
    const value = e.target.value.replace(/\D/g, '');
    const formattedCnpj = value
      .replace(/^(\d{2})(\d)/, '$1.$2')
      .replace(/^(\d{2})\.(\d{3})(\d)/, '$1.$2.$3')
      .replace(/\.(\d{3})(\d)/, '.$1/$2')
      .replace(/(\d{4})(\d)/, '$1-$2')
      .slice(0, 18);
    setCnpj(formattedCnpj);
  };

  const handleToggleChange = () => {
    const newTestLocalState = !isTestLocal;
    setIsTestLocalState(newTestLocalState);
    setIsTestLocal(newTestLocalState); // Informa o estado ao SearchPage
    if (newTestLocalState) {
      setIsWarningModalOpen(true); // Abre o modal quando o toggle é ativado
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!cnpj || cnpj.length < 18) {
      toast.error('Por favor, insira um CNPJ válido.');
      return;
    }

    if (!isTestLocal) {
      if (!dataIni) {
        toast.error('Por favor, preencha a data de início.');
        return;
      }

      if (!dataFim) {
        toast.error('Por favor, preencha a data de fim.');
        return;
      }

      const dataInicial = new Date(dataIni);
      const dataFinal = new Date(dataFim);
      const diffTime = Math.abs(dataFinal - dataInicial);
      const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

      if (diffDays > 365) {
        toast.error('A diferença entre as datas não pode ser superior a 365 dias.');
        return;
      }
    }

    const formattedCnpj = cnpj.replace(/\D/g, '');
    const formattedDataIni = dataIni.replace(/-/g, '');
    const formattedDataFim = dataFim.replace(/-/g, '');

    if (isTestLocal) {
      onSearch(formattedCnpj);
    } else {
      onSearch(formattedCnpj, formattedDataIni, formattedDataFim);
    }
  };

  return (
    <div
      id="orgSearchForm"
      className="relative flex flex-col items-center justify-center p-8 mb-8"
      style={{ width: '100%', minHeight: '10vh' }}
    >
      {/* Warning Modal */}
      <WarningModal isOpen={isWarningModalOpen} onClose={() => setIsWarningModalOpen(false)} />

      {/* Título e Formulário */}
      <div>
        <h1 className="text-3xl lg:text-5xl font-bold text-black mb-2 mt-8">
          Faça a sua pesquisa com{' '}
          <span className="text-blue-600">Facilidade</span>
        </h1>
      </div>

      <div className="relative w-full max-w-2xl p-8 bg-white border border-blue-300 rounded-lg shadow-lg mt-6 mb-8">
        
        {/* Toggle para Teste Local */}
        <div className="absolute top-2 right-2 flex items-center">
          <span className="text-sm font-medium text-blue-700 mr-2">Teste Local</span>
          <label className="inline-flex relative items-center cursor-pointer">
            <input
              type="checkbox"
              className="sr-only peer"
              checked={isTestLocal}
              onChange={handleToggleChange}
            />
            <div className="w-11 h-6 bg-gray-200 rounded-full peer peer-checked:after:translate-x-full after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
          </label>
        </div>
        
        <form className="space-y-6" onSubmit={handleSubmit}>
          <h2 className="text-2xl font-semibold text-blue-700 text-center">
            Consulta de Contratos por Órgão
          </h2>
          <p className="text-blue-600 text-sm text-center">
            Utilize o CNPJ e {isTestLocal ? 'clique em pesquisar para usar os dados locais' : 'o período desejado para realizar a busca'}
          </p>

          <div>
            <label htmlFor="cnpj" className="block mb-2 text-sm font-medium text-blue-700">CNPJ:</label>
            <input
              type="text"
              name="cnpj"
              id="cnpj"
              value={cnpj}
              onChange={handleCNPJInput}
              className="bg-gray-50 border border-gray-300 text-gray-700 text-sm rounded-lg focus:ring-0 focus:border-gray-300 block w-full p-2.5 placeholder-gray-400"
              placeholder="00.000.000/0000-00"
            />
          </div>

          {!isTestLocal && (
            <div className="flex flex-col sm:flex-row sm:space-x-4 space-y-4 sm:space-y-0">
              <div className="flex-1">
                <label htmlFor="dataIni" className="block mb-2 text-sm font-medium text-blue-700">Data de Início:</label>
                <input
                  type="date"
                  name="dataIni"
                  id="dataIni"
                  value={dataIni}
                  onChange={(e) => setDataIni(e.target.value)}
                  className="bg-gray-50 border border-gray-300 text-gray-700 text-sm rounded-lg focus:ring-0 focus:border-gray-300 w-full p-2.5 placeholder-gray-400"
                />
              </div>

              <div className="flex-1">
                <label htmlFor="dataFim" className="block mb-2 text-sm font-medium text-blue-700">Data de Fim:</label>
                <input
                  type="date"
                  name="dataFim"
                  id="dataFim"
                  value={dataFim}
                  onChange={(e) => setDataFim(e.target.value)}
                  className="bg-gray-50 border border-gray-300 text-gray-700 text-sm rounded-lg focus:ring-0 focus:border-gray-300 w-full p-2.5 placeholder-gray-400"
                />
              </div>
            </div>
          )}

          <button
            type="submit"
            className="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center"
          >
            Realizar Pesquisa
          </button>
        </form>
      </div>
    </div>
  );
};

export default OrgSearchForm;
