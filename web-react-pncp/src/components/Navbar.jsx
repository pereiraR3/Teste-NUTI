import React, { useState } from 'react'; // Importa React e o hook useState para gerenciamento de estado
import NavbarDesktop from './NavbarDesktop'; // Componente de navbar para visualização em desktop
import NavbarMobile from './NavbarMobile'; // Componente de navbar para visualização em dispositivos móveis
import useMediaQuery from '../hooks/useMediaQuery'; // Hook personalizado para verificar a largura da tela

// Componente Navbar que lida com a visualização da barra de navegação em dispositivos desktop e móveis
const Navbar = ({ triggerModal }) => {
  // Usa o hook personalizado useMediaQuery para determinar se a largura da tela é de um desktop (maior que 768px)
  const isDesktop = useMediaQuery('(min-width: 768px)');

  // useState para controlar se a navegação lateral (sidebar) está aberta ou fechada em dispositivos móveis
  const [isOpen, setIsOpen] = useState(false);

  // Função que faz a rolagem suave para uma seção específica da página usando o ID da seção
  const handleScrollToSection = (sectionId) => {
    const section = document.getElementById(sectionId); // Obtém o elemento pelo ID
    if (section) {
      const offset = window.innerHeight * 0.2; // Define um deslocamento de 20% da altura da janela
      const sectionPosition = section.getBoundingClientRect().top + window.pageYOffset - offset;
      
      // Rola suavemente para a posição da seção ajustada pelo offset
      window.scrollTo({
        top: sectionPosition,
        behavior: 'smooth' // Comportamento suave de rolagem
      });
    }
  };

  // Lista de itens da navbar, cada item tem um texto e uma ação associada (triggerModal ou rolagem para uma seção)
  const navItems = [
    { id: 1, text: 'Aviso', action: triggerModal }, // Item que aciona o modal
    { id: 2, text: 'Como pesquisar', action: () => handleScrollToSection('searchExplanation') } // Item que rola até a seção "Como pesquisar"
  ];

  // Função que alterna a visibilidade da navegação móvel (sidebar)
  const toggleMobileNav = () => {
    setIsOpen(!isOpen); // Alterna o estado de isOpen entre true e false
  };

  // Renderização condicional da navbar. Se a tela for de desktop, renderiza NavbarDesktop; senão, renderiza NavbarMobile
  return (
    <>
      {isDesktop ? (
        // Renderiza NavbarDesktop se a largura da tela for maior que 768px
        <NavbarDesktop navItems={navItems} />
      ) : (
        // Renderiza NavbarMobile se a largura da tela for menor que 768px
        <NavbarMobile
          navItems={navItems} // Passa os itens de navegação
          isOpen={isOpen} // Estado da visibilidade do menu móvel
          toggleMobileNav={toggleMobileNav} // Função para abrir/fechar o menu móvel
        />
      )}
    </>
  );
};

export default Navbar; // Exporta o componente Navbar para ser usado em outras partes da aplicação
