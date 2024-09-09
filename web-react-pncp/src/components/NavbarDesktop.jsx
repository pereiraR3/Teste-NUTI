import React from 'react'; // Importa o React, necessário para criar componentes funcionais em React
import logo from '../assets/logo_gov.png'; // Importa a imagem do logotipo, que será exibida na navbar

// Componente NavbarDesktop: Recebe os itens de navegação (navItems) como prop
const NavbarDesktop = ({ navItems }) => {

  // Função que rola suavemente a página até o formulário de busca
  const handleScrollToSearch = () => {
    const searchForm = document.getElementById('orgSearchForm'); // Obtém o formulário de busca pelo ID
    if (searchForm) {
      searchForm.scrollIntoView({ behavior: 'smooth' }); // Rola suavemente até o formulário de busca
    }
  };

  // Renderiza o JSX que compõe a estrutura da navbar
  return (
    <nav className="bg-white shadow-md fixed top-0 left-0 w-full z-50">
      {/* Contêiner que centraliza o conteúdo da navbar */}
      <div className="container mx-auto flex justify-between items-center h-20 px-4">
        
        {/* Seção do logotipo e título */}
        <div className="flex items-center">
          <img src={logo} alt="gov.br" className="h-8 mr-4" /> {/* Exibe o logotipo com altura definida */}
          <h1 className="text-xl font-bold text-blue-700">
            Portal Nacional de Contratações Públicas - PNCP
          </h1> {/* Título ao lado do logotipo, estilizado com cor azul e fonte negrito */}
        </div>

        {/* Seção de navegação (itens de menu) */}
        <div className="flex items-center space-x-6">
          {/* Lista de itens de navegação mapeados a partir da prop navItems */}
          <ul className="flex space-x-4">
            {navItems.map((item) => (
              <li
                key={item.id} // Cada item de navegação tem uma chave única (necessário para renderização de listas no React)
                className="text-sm text-blue-600 cursor-pointer hover:text-blue-800 transition-colors duration-300"
                onClick={item.action ? item.action : null} // Define a ação de clique se houver ação associada
              >
                {item.text} {/* Exibe o texto do item de navegação */}
              </li>
            ))}
          </ul>

          {/* Botão que, ao ser clicado, rola até o formulário de busca */}
          <button
            className="bg-blue-700 text-white px-4 py-2 rounded transition-colors duration-300 ease-in-out hover:bg-blue-800"
            onClick={handleScrollToSearch} // Aciona a função de rolar até o formulário de busca ao clicar
          >
            Faça sua pesquisa
          </button>
        </div>
      </div>
    </nav>
  );
};

export default NavbarDesktop; // Exporta o componente para ser usado em outras partes da aplicação
