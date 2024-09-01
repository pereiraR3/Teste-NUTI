import React from 'react';
import { AiOutlineClose, AiOutlineMenu } from 'react-icons/ai';
import logo from '../assets/logo_gov.png';

const NavbarMobile = ({ navItems, isOpen, toggleMobileNav }) => {

  const handleScrollToSearch = () => {
    const searchForm = document.getElementById('orgSearchForm');
    if (searchForm) {
      searchForm.scrollIntoView({ behavior: 'smooth' });
    }
    toggleMobileNav(); // Fecha o menu após a rolagem
  };

  return (
    <>
      {!isOpen && (
        <nav className="bg-white shadow-md fixed top-0 left-0 w-full z-50">
          <div className="flex justify-between items-center h-20 px-4">
            {/* Logo */}
            <div className="flex items-center">
              <img src={logo} alt="gov.br" className="h-8 mr-4" />
            </div>

            {/* Mobile Navigation Icon */}
            <div onClick={toggleMobileNav} className="cursor-pointer">
              <AiOutlineMenu size={25} />
            </div>
          </div>
        </nav>
      )}

      {isOpen && (
        <div className="fixed inset-0 z-50">
          <div
            className="fixed top-0 left-0 w-full h-full bg-gray-900 bg-opacity-50 z-40"
            onClick={toggleMobileNav}
          />
          <div
            className="fixed top-0 left-0 w-[250px] h-full bg-white shadow-lg z-50 transition-transform duration-300 ease-in-out"
            onClick={(e) => e.stopPropagation()}
          >
            <div className="flex justify-between items-center p-4 border-b border-gray-300">
              <img src={logo} alt="gov.br" className="h-8 mr-4" />
              <AiOutlineClose size={25} className="cursor-pointer" onClick={toggleMobileNav} />
            </div>

            <ul className="p-4">
              {navItems.map((item) => (
                <li
                  key={item.id}
                  className="p-4 text-sm text-blue-600 cursor-pointer border-b border-gray-200 hover:bg-blue-50 hover:text-blue-800 transition-colors duration-300"
                >
                  {item.text}
                </li>
              ))}
              <li className="p-4">
                <button
                  className="w-full bg-blue-700 text-white py-2 rounded transition-colors duration-300 ease-in-out hover:bg-blue-800"
                  onClick={handleScrollToSearch}
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

export default NavbarMobile;
