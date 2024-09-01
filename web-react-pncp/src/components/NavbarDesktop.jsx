import React from 'react';
import logo from '../assets/logo_gov.png';

const NavbarDesktop = ({ navItems }) => {

  const handleScrollToSearch = () => {
    const searchForm = document.getElementById('orgSearchForm');
    if (searchForm) {
      searchForm.scrollIntoView({ behavior: 'smooth' });
    }
  };

  return (
    <nav className="bg-white shadow-md fixed top-0 left-0 w-full z-50">
      <div className="container mx-auto flex justify-between items-center h-20 px-4">
        {/* Logo */}
        <div className="flex items-center">
          <img src={logo} alt="gov.br" className="h-8 mr-4" />
          <h1 className="text-xl font-bold text-blue-700">Portal Nacional de Contratações Públicas - PNCP</h1>
        </div>

        {/* Desktop Navigation */}
        <div className="flex items-center space-x-6">
          <ul className="flex space-x-4">
            {navItems.map((item) => (
              <li
                key={item.id}
                className="text-sm text-blue-600 cursor-pointer hover:text-blue-800 transition-colors duration-300"
              >
                {item.text}
              </li>
            ))}
          </ul>
          <button
            className="bg-blue-700 text-white px-4 py-2 rounded transition-colors duration-300 ease-in-out hover:bg-blue-800"
            onClick={handleScrollToSearch} // Evento de clique para rolar suavemente
          >
            Faça sua pesquisa
          </button>
        </div>
      </div>
    </nav>
  );
};

export default NavbarDesktop;
