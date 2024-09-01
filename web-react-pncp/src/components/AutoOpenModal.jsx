import React, { useEffect } from 'react';

const AutoOpenModal = ({ isOpen, onClose }) => {
  useEffect(() => {
    if (isOpen) {
      const timer = setTimeout(() => {
        onClose();
      }, 10000); // Fecha automaticamente após 10 segundos

      return () => clearTimeout(timer); // Limpa o timer se o modal for fechado manualmente
    }
  }, [isOpen, onClose]);

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 flex items-center justify-center z-50 bg-black bg-opacity-50">
      <div className="bg-white p-6 rounded-lg shadow-lg max-w-sm w-full">
        <h2 className="text-xl font-semibold text-blue-700 mb-4">Aviso Importante</h2>
        <p className="text-sm text-gray-700 mb-4">
          Este website é destinado à resolução de um desafio técnico proposto pela Nuti. 
          Todas as funcionalidades e informações contidas aqui têm o propósito de demonstrar habilidades técnicas.
        </p>
        <button
          onClick={onClose}
          className="w-full bg-blue-700 text-white py-2 rounded hover:bg-blue-800 transition-colors duration-300"
        >
          Entendi
        </button>
      </div>
    </div>
  );
};

export default AutoOpenModal;
