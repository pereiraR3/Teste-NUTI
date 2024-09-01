import React, { useState } from 'react';
import NavbarDesktop from './NavbarDesktop';
import NavbarMobile from './NavbarMobile';
import useMediaQuery from '../hooks/useMediaQuery';

const Navbar = () => {
  const isDesktop = useMediaQuery('(min-width: 768px)');
  const [isOpen, setIsOpen] = useState(false); // Estado para controlar a visibilidade da Navbar e Sidebar

  const navItems = [
    { id: 1, text: 'Sobre' },
    { id: 2, text: 'Como pesquisar' },
    { id: 3, text: 'Aviso' }
  ];

  const toggleMobileNav = () => {
    setIsOpen(!isOpen); // Alterna entre abrir e fechar a Sidebar
  };

  return (
    <>
      {isDesktop ? (
        <NavbarDesktop navItems={navItems} />
      ) : (
        <NavbarMobile
          navItems={navItems}
          isOpen={isOpen}
          toggleMobileNav={toggleMobileNav}
        />
      )}
    </>
  );
};

export default Navbar;
