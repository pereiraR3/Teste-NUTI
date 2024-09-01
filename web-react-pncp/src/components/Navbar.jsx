import React, { useState } from 'react';
import NavbarDesktop from './NavbarDesktop';
import NavbarMobile from './NavbarMobile';
import useMediaQuery from '../hooks/useMediaQuery';

const Navbar = ({triggerModal}) => {
  const isDesktop = useMediaQuery('(min-width: 768px)');
  const [isOpen, setIsOpen] = useState(false); // Estado para controlar a visibilidade da Navbar e Sidebar

  const handleScrollToSection = (sectionId) => {
    const section = document.getElementById(sectionId);
    if (section) {
      const offset = window.innerHeight * 0.2; // 20% da altura da janela
      const sectionPosition = section.getBoundingClientRect().top + window.pageYOffset - offset;
      
      window.scrollTo({
        top: sectionPosition,
        behavior: 'smooth'
      });
    }
  };  
  
  const navItems = [
    { id: 1, text: 'Aviso', action: triggerModal},
    { id: 2, text: 'Como pesquisar', action: () => handleScrollToSection('searchExplanation') }
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
