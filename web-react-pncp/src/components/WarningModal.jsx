import React from 'react'; // Importa React para criar o componente funcional

// Componente WarningModal: Exibe um modal de aviso quando o usuário ativa o modo de teste
const WarningModal = ({ isOpen, onClose }) => {
  // Se o modal não estiver aberto (isOpen for false), não renderiza nada
  if (!isOpen) return null;

  // Caso o modal esteja aberto (isOpen for true), renderiza o conteúdo abaixo
  return (
    // Contêiner que cobre toda a tela com um fundo semitransparente escuro
    <div className="fixed inset-0 flex items-center justify-center z-50 bg-black bg-opacity-50">
      {/* Caixa do modal com fundo branco, bordas arredondadas, sombra e padding */}
      <div className="bg-white p-6 rounded-lg shadow-lg max-w-sm w-full">
        
        {/* Título do modal */}
        <h2 className="text-xl font-semibold text-blue-700 mb-4">Aviso</h2>
        
        {/* Texto explicativo sobre o funcionamento do modo de teste */}
        <p className="text-sm text-gray-700 mb-4">
          Ao deixar marcado o toggle, a pesquisa funcionará de acordo com os dados que já existem no banco de dados e não em relação ao consumo de dados da API.
        </p>

        {/* Informação adicional sobre os CNPJs que têm dados no banco */}
        <p className="text-sm text-gray-700 mb-4">
          Existem dados relacionados aos seguintes CNPJs no banco de dados:
        </p>

        {/* Lista de CNPJs para os quais existem dados disponíveis */}
        <ul className="list-disc list-inside text-sm text-gray-700 mb-4">
          <li>11.111.111/1111-11</li>
          <li>00.000.000/0000-00</li>
        </ul>

        {/* Informação adicional sobre o período dos dados disponíveis */}
        <p className="text-sm text-gray-700 mb-4">
          Os dados disponíveis são para o período de 2023-01-01 a 2023-12-31.
        </p>

        {/* Botão para fechar o modal */}
        <button
          onClick={onClose} // Fecha o modal quando o botão é clicado
          className="w-full bg-blue-700 text-white py-2 rounded hover:bg-blue-800 transition-colors duration-300"
        >
          Entendi
        </button>
      </div>
    </div>
  );
};

export default WarningModal; // Exporta o componente para ser utilizado em outras partes da aplicação
