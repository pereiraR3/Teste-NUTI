import React from 'react';
import gif from '../assets/storyset.gif';

const HeroSection = () => {
  const handleScrollToSearch = () => {
    const searchForm = document.getElementById('orgSearchForm');
    if (searchForm) {
      searchForm.scrollIntoView({ behavior: 'smooth' });
    }
  };

  return (
    <div className="flex flex-col-reverse lg:flex-row justify-between items-center min-h-[60vh] p-4 lg:p-6 bg-white mt-10">
      {/* Texto Chamativo */}
      <div className="w-full lg:w-1/2 mt-4 lg:mt-0 text-center lg:text-left px-4 lg:px-6">
        <h1 className="text-2xl lg:text-4xl font-bold text-black">
          Visualize e Gerencie{' '}
          <span className="text-blue-600">Contratos Públicos</span> com Facilidade
        </h1>
        <p className="text-sm lg:text-base text-gray-700 mt-2 lg:mt-4">
          Essa plataforma foi desenvolvida para fornecer uma visão abrangente dos contratos de órgãos públicos.
          A partir do CNPJ do órgão e de um período específico, você pode acessar informações detalhadas
          e visualizar todos os contratos relevantes, além de monitorar o valor total dos contratos.
        </p>
        <button
          onClick={handleScrollToSearch}
          className="mt-4 lg:mt-6 bg-blue-600 text-white text-sm lg:text-base font-medium px-5 py-2 rounded-lg shadow-md hover:bg-blue-700 transition duration-300"
        >
          Comece Agora
        </button>
      </div>

      {/* Imagem ou GIF ao Lado Direito */}
      <div className="w-full lg:w-1/2 flex justify-center lg:justify-end items-center px-4 lg:px-6 mt-6 lg:mt-0">
        <img src={gif} alt="Descrição da Imagem" className="max-w-xs md:max-w-md lg:max-w-lg h-auto mt-4 lg:mt-0" />
      </div>
    </div>
  );
};

export default HeroSection;
