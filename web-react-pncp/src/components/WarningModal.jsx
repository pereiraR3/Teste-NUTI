import React from 'react';

const WarningModal = ({ isOpen, onClose }) => {
  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 flex items-center justify-center z-50 bg-black bg-opacity-50">
      <div className="bg-white p-6 rounded-lg shadow-lg max-w-sm w-full">
        <h2 className="text-xl font-semibold text-blue-700 mb-4">Aviso</h2>
        <p className="text-sm text-gray-700 mb-4">
          Ao deixar marcado o toggle, a pesquisa funcionará de acordo com os dados que já existem no banco de dados e não em relação ao consumo de dados da API.
        </p>
        <p className="text-sm text-gray-700 mb-4">
          Existem dados relacionados aos seguintes CNPJs no banco de dados:
        </p>
        <ul className="list-disc list-inside text-sm text-gray-700 mb-4">
          <li>15.084.338/0001-46</li>
          <li>33.004.540/0001-00</li>
        </ul>
        <p className="text-sm text-gray-700 mb-4">
          Os dados disponíveis são para o período de 2023-01-01 a 2023-12-31.
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

export default WarningModal;
