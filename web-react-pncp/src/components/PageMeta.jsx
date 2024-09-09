import React from 'react';
import { Helmet } from 'react-helmet'; // Biblioteca react-helmet para manipular o head do HTML

// Componente PageMeta que configura o título e o favicon da página
const PageMeta = ({ title, favicon }) => {
  return (
    // Helmet é utilizado para modificar o conteúdo do <head> do documento HTML
    <Helmet>
      {/* Define o título da página dinâmicamente com base na prop 'title' */}
      <title>{title}</title>
      
      {/* Define o favicon da página com base na prop 'favicon', aceita imagens no formato PNG */}
      <link rel="icon" type="image/png" href={favicon} />
    </Helmet>
  );
};

export default PageMeta;
