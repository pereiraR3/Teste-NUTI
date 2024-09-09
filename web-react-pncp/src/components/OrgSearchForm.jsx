import React, { useState } from 'react'; // Importa React e useState para gerenciamento de estado
import { toast } from 'react-toastify'; // Importa a biblioteca react-toastify para exibir notificações
import 'react-toastify/dist/ReactToastify.css'; // Importa os estilos do react-toastify
import '../styles/variables.css'; // Importa o arquivo CSS para estilos personalizados
import WarningModal from './WarningModal'; // Importa o componente WarningModal

// Componente OrgSearchForm: Formulário de pesquisa de contratos de um órgão
const OrgSearchForm = ({ onSearch, setIsTestLocal }) => {
  // Estados para armazenar os valores do formulário
  const [cnpj, setCnpj] = useState(''); // Estado para o CNPJ
  const [dataIni, setDataIni] = useState(''); // Estado para a data de início
  const [dataFim, setDataFim] = useState(''); // Estado para a data de fim
  const [isTestLocal, setIsTestLocalState] = useState(false); // Estado para controlar se o modo de teste local está ativado
  const [isWarningModalOpen, setIsWarningModalOpen] = useState(false); // Estado para controlar a visibilidade do modal de aviso
  const [isLoading, setIsLoading] = useState(false); // Estado para indicar se o formulário está carregando

  // Função para formatar a entrada de CNPJ
  const handleCNPJInput = (e) => {
    const value = e.target.value.replace(/\D/g, ''); // Remove caracteres não numéricos
    const formattedCnpj = value
      .replace(/^(\d{2})(\d)/, '$1.$2')
      .replace(/^(\d{2})\.(\d{3})(\d)/, '$1.$2.$3')
      .replace(/\.(\d{3})(\d)/, '.$1/$2')
      .replace(/(\d{4})(\d)/, '$1-$2')
      .slice(0, 18); // Limita o tamanho do CNPJ a 18 caracteres
    setCnpj(formattedCnpj); // Atualiza o estado do CNPJ
  };

  // Função para alternar o modo de teste local (toggle)
  const handleToggleChange = () => {
    const newTestLocalState = !isTestLocal; // Alterna o estado de isTestLocal
    setIsTestLocalState(newTestLocalState); // Atualiza o estado localmente
    setIsTestLocal(newTestLocalState); // Informa o estado ao componente pai (SearchPage)
    if (newTestLocalState) {
      setIsWarningModalOpen(true); // Abre o modal de aviso se o modo de teste local for ativado
    }
  };

  // Função para processar o envio do formulário
  const handleSubmit = async (e) => {
    e.preventDefault(); // Previne o comportamento padrão do formulário

    // Validações de CNPJ
    if (!cnpj || cnpj.length < 18) {
      toast.error('Por favor, insira um CNPJ válido.'); // Exibe uma mensagem de erro se o CNPJ for inválido
      return;
    }

    // Validações para o modo normal (não é o modo de teste local)
    if (!isTestLocal) {
      if (!dataIni) {
        toast.error('Por favor, preencha a data de início.');
        return;
      }

      if (!dataFim) {
        toast.error('Por favor, preencha a data de fim.');
        return;
      }

      if (dataFim === dataIni) {
        toast.error('Por favor, insira datas distintas.');
        return;
      }

      const dataInicial = new Date(dataIni); // Converte a data de início para um objeto Date
      const dataFinal = new Date(dataFim); // Converte a data de fim para um objeto Date
      const diffTime = Math.abs(dataFinal - dataInicial); // Calcula a diferença de tempo entre as datas
      const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)); // Converte a diferença para dias

      if (dataFinal - dataInicial < 0) {
        toast.error('Por favor, a data de fim deve ser após a data de início.');
        return;
      }

      if (diffDays > 365) {
        toast.error('A diferença entre as datas não pode ser superior a 365 dias.');
        return;
      }
    }

    setIsLoading(true); // Define o estado de carregamento para verdadeiro

    try {
      const formattedCnpj = cnpj.replace(/\D/g, ''); // Remove caracteres não numéricos do CNPJ
      const formattedDataIni = dataIni.replace(/-/g, ''); // Formata a data de início
      const formattedDataFim = dataFim.replace(/-/g, ''); // Formata a data de fim

      // Chama a função de busca dependendo se o modo de teste local está ativado
      if (isTestLocal) {
        await onSearch(formattedCnpj); // Executa a busca no modo de teste local
      } else {
        await onSearch(formattedCnpj, formattedDataIni, formattedDataFim); // Executa a busca no modo normal
      }
    } catch (error) {
      toast.error('Ocorreu um erro durante a pesquisa. Tente novamente.');
    } finally {
      setIsLoading(false); // Define o estado de carregamento para falso
    }
  };

  return (
    <div
      id="orgSearchForm"
      className="relative flex flex-col items-center justify-center p-10"
      style={{ width: '100%', minHeight: '10vh' }}
    >
      {/* Modal de Aviso */}
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

          {/* Campos de data visíveis apenas quando não estiver no modo de teste local */}
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

          {/* Botão de submissão do formulário */}
          <button
            type="submit"
            className={`w-full text-white font-medium rounded-lg text-sm px-5 py-2.5 text-center 
              ${isLoading ? 'bg-gray-400 cursor-not-allowed' : 'bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300'}`}
            disabled={isLoading} // Desativa o botão enquanto o carregamento estiver ativo
          >
            {isLoading ? 'Carregando...' : 'Realizar Pesquisa'}
          </button>
        </form>
      </div>
    </div>
  );
};

export default OrgSearchForm; // Exporta o componente
