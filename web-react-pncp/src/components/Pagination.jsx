import React from 'react'; // Importa React para criar o componente funcional

// Componente Pagination: Exibe uma barra de paginação com botões para navegar entre as páginas
const Pagination = ({ totalPages, currentPage, paginate }) => {
  return (
    <div className="py-2">
      <nav className="block">
        {/* Lista de botões de paginação */}
        <ul className="flex pl-0 rounded list-none flex-wrap">
          
          {/* Botão para voltar uma página */}
          <li>
            <button
              onClick={() => paginate(currentPage - 1)} // Vai para a página anterior
              disabled={currentPage === 1} // Desativa o botão se o usuário já estiver na primeira página
              className="first:ml-0 text-xs font-semibold flex w-8 h-8 mx-1 p-0 rounded-full items-center justify-center leading-tight relative border border-solid border-lightBlue-500 bg-white text-lightBlue-500"
            >
              {/* Ícone da seta para a esquerda */}
              <i className="fas fa-chevron-left -ml-px"></i>
            </button>
          </li>

          {/* Gera os botões numerados de acordo com o total de páginas */}
          {Array.from({ length: totalPages }, (_, i) => i + 1).map((page) => (
            <li key={page}>
              <button
                onClick={() => paginate(page)} // Vai para a página clicada
                className={`first:ml-0 text-xs font-semibold flex w-8 h-8 mx-1 p-0 rounded-full items-center justify-center leading-tight relative border border-solid border-lightBlue-500 ${
                  page === currentPage ? 'bg-lightBlue-500 text-white' : 'bg-white text-lightBlue-500'
                }`}
              >
                {page} {/* Número da página */}
              </button>
            </li>
          ))}

          {/* Botão para avançar uma página */}
          <li>
            <button
              onClick={() => paginate(currentPage + 1)} // Vai para a próxima página
              disabled={currentPage === totalPages} // Desativa o botão se o usuário já estiver na última página
              className="first:ml-0 text-xs font-semibold flex w-8 h-8 mx-1 p-0 rounded-full items-center justify-center leading-tight relative border border-solid border-lightBlue-500 bg-white text-lightBlue-500"
            >
              {/* Ícone da seta para a direita */}
              <i className="fas fa-chevron-right -mr-px"></i>
            </button>
          </li>
        </ul>
      </nav>
    </div>
  );
};

export default Pagination; // Exporta o componente para ser utilizado em outras partes da aplicação
