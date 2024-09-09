import React, { useEffect } from 'react'; // Importa React e o hook useEffect para efeitos colaterais

// Componente AutoOpenModal: Um modal que se fecha automaticamente após 10 segundos
const AutoOpenModal = ({ isOpen, onClose }) => {
  
  // Hook useEffect para lidar com o comportamento de fechamento automático
  useEffect(() => {
    if (isOpen) {
      // Inicia um timer que fecha o modal automaticamente após 10 segundos
      const timer = setTimeout(() => {
        onClose(); // Chama a função onClose para fechar o modal
      }, 10000); // O tempo é definido como 10 segundos (10.000 milissegundos)

      // Função de limpeza: remove o timer caso o modal seja fechado manualmente antes dos 10 segundos
      return () => clearTimeout(timer);
    }
  }, [isOpen, onClose]); // useEffect depende de isOpen e onClose. Reexecuta quando algum deles mudar

  // Se o modal não estiver aberto (isOpen for false), retorna null e não renderiza nada
  if (!isOpen) return null;

  // Se o modal estiver aberto, renderiza a estrutura abaixo
  return (
    <div className="fixed inset-0 flex items-center justify-center z-50 bg-black bg-opacity-50">
      {/* Caixa do modal */}
      <div className="bg-white p-6 rounded-lg shadow-lg max-w-sm w-full">
        {/* Título do modal */}
        <h2 className="text-xl font-semibold text-blue-700 mb-4">Aviso Importante</h2>
        
        {/* Texto explicativo */}
        <p className="text-sm text-gray-700 mb-4">
          Este website é destinado à resolução de um desafio técnico proposto pela Nuti. 
          Todas as funcionalidades e informações contidas aqui têm o propósito de demonstrar habilidades técnicas.
        </p>
        
        {/* Botão para fechar o modal manualmente */}
        <button
          onClick={onClose} // Fecha o modal ao clicar no botão
          className="w-full bg-blue-700 text-white py-2 rounded hover:bg-blue-800 transition-colors duration-300"
        >
          Entendi
        </button>
      </div>
    </div>
  );
};

export default AutoOpenModal; // Exporta o componente para ser utilizado em outras partes da aplicação
