// src/components/ErrorModal.jsx
import React from 'react'; // Importa React para criar o componente funcional

// Componente ErrorModal: Exibe uma janela modal com uma mensagem de erro e um botão de fechar
const ErrorModal = ({ message, onClose }) => {
  return (
    // Modal de fundo escuro transparente, centralizado na tela
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      
      {/* Caixa do modal com fundo branco, padding e bordas arredondadas */}
      <div className="bg-white p-6 rounded-lg shadow-lg w-96">
        
        {/* Título do modal, estilizado em vermelho para indicar erro */}
        <h2 className="text-xl font-bold text-red-600 mb-4">Erro na Requisição</h2>
        
        {/* Mensagem de erro recebida como prop */}
        <p className="text-gray-700 mb-4">{message}</p>

        {/* Botão para fechar o modal */}
        <button
          onClick={onClose} // Chama a função onClose para fechar o modal
          className="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700"
        >
          Fechar
        </button>
      </div>
    </div>
  );
};

export default ErrorModal; // Exporta o componente para ser utilizado em outras partes da aplicação
