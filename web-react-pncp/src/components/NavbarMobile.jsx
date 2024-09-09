import React from 'react'; // Importa o React, necessário para criar componentes funcionais
import { AiOutlineClose, AiOutlineMenu } from 'react-icons/ai'; // Importa ícones para abrir/fechar o menu móvel
import logo from '../assets/logo_gov.png'; // Importa a logo que será exibida na navbar

// Componente NavbarMobile: Recebe os itens de navegação (navItems), o estado de abertura do menu (isOpen), e a função para alternar o menu (toggleMobileNav)
const NavbarMobile = ({ navItems, isOpen, toggleMobileNav }) => {

  // Função que rola suavemente até o formulário de busca e fecha o menu após a rolagem
  const handleScrollToSearch = () => {
    const searchForm = document.getElementById('orgSearchForm'); // Obtém o formulário de busca pelo ID
    if (searchForm) {
      searchForm.scrollIntoView({ behavior: 'smooth' }); // Rola suavemente até o formulário de busca
    }
    toggleMobileNav(); // Fecha o menu após a rolagem
  };

  return (
    <>
      {/* Exibe a navbar com o ícone do menu apenas se o menu móvel não estiver aberto */}
      {!isOpen && (
        <nav className="bg-white shadow-md fixed top-0 left-0 w-full z-50">
          <div className="flex justify-between items-center h-20 px-4">
            {/* Logo */}
            <div className="flex items-center">
              <img src={logo} alt="gov.br" className="h-8 mr-4" /> {/* Exibe o logotipo */}
            </div>

            {/* Ícone de navegação móvel (menu) */}
            <div onClick={toggleMobileNav} className="cursor-pointer">
              {/* Alterna entre o ícone de menu (AiOutlineMenu) e o ícone de fechar (AiOutlineClose) */}
              {isOpen ? <AiOutlineClose size={25} /> : <AiOutlineMenu size={25} />}
            </div>
          </div>
        </nav>
      )}

      {/* Exibe o menu móvel (sidebar) se estiver aberto */}
      {isOpen && (
        <div className="fixed inset-0 z-50">
          {/* Fundo escuro semitransparente que cobre toda a tela. Fecha o menu ao clicar nele */}
          <div
            className="fixed top-0 left-0 w-full h-full bg-gray-900 bg-opacity-50 z-40"
            onClick={toggleMobileNav}
          />
          {/* Sidebar que contém os itens de navegação */}
          <div
            className="fixed top-0 left-0 w-[250px] h-full bg-white shadow-lg z-50 transition-transform duration-300 ease-in-out"
            onClick={(e) => e.stopPropagation()} // Impede que o clique dentro da sidebar feche o menu
          >
            {/* Cabeçalho da sidebar com a logo e o ícone de fechar */}
            <div className="flex justify-between items-center p-4 border-b border-gray-300">
              <img src={logo} alt="gov.br" className="h-8 mr-4" /> {/* Exibe o logotipo na sidebar */}
              <AiOutlineClose size={25} className="cursor-pointer" onClick={toggleMobileNav} /> {/* Ícone para fechar o menu */}
            </div>

            {/* Lista de itens de navegação */}
            <ul className="p-4">
              {navItems.map((item) => (
                <li
                  key={item.id} // Define uma chave única para cada item de navegação
                  className="p-4 text-sm text-blue-600 cursor-pointer border-b border-gray-200 hover:bg-blue-50 hover:text-blue-800 transition-colors duration-300"
                  onClick={() => {
                    if (item.action) item.action(); // Executa a ação associada ao item, se houver
                    toggleMobileNav(); // Fecha o menu após clicar no item
                  }}
                >
                  {item.text} {/* Exibe o texto do item de navegação */}
                </li>
              ))}
              
              {/* Botão para rolar até o formulário de busca */}
              <li className="p-4">
                <button
                  className="w-full bg-blue-700 text-white py-2 rounded transition-colors duration-300 ease-in-out hover:bg-blue-800"
                  onClick={handleScrollToSearch} // Aciona a função para rolar até o formulário e fechar o menu
                >
                  Faça a sua pesquisa
                </button>
              </li>
            </ul>
          </div>
        </div>
      )}
    </>
  );
};

export default NavbarMobile; // Exporta o componente para ser utilizado em outras partes da aplicação
