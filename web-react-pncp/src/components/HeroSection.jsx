import React from 'react';
import gif from '../assets/storyset.gif';

const HeroSection = () => {
  return (
    <div className="flex flex-col-reverse lg:flex-row justify-between items-center min-h-screen p-4 bg-white">

      {/* Texto Chamativo */}
      <div className="w-full lg:w-1/2 mt-4 lg:mt-0 text-center lg:text-left px-4 lg:px-8">

        <h1 className="text-3xl lg:text-5xl font-bold text-black">
          Visualize e Gerencie{' '}
          <span className="text-blue-600">Contratos Públicos</span> com Facilidade
        </h1>

        <p className="text-base lg:text-lg text-gray-700 mt-2 lg:mt-8">
          Essa plataforma foi desenvolvida para fornecer uma visão abrangente dos contratos de órgãos públicos. 
          A partir do CNPJ do órgão e de um período específico, você pode acessar informações detalhadas 
          e visualizar todos os contratos relevantes, além de monitorar o valor total dos contratos.
        </p>

        <button className="mt-6 lg:mt-8 bg-blue-600 text-white text-base lg:text-lg font-medium px-6 py-3 rounded-lg shadow-md hover:bg-blue-700 transition duration-300">
          Comece Agora
        </button>

      </div>

      {/* Imagem ou GIF ao Lado Direito */}
      <div className="w-full lg:w-1/2 flex justify-center lg:justify-end items-center px-4 lg:px-10 mt-10 lg:mt-0">
        <img src={gif} alt="Descrição da Imagem" className="max-w-xs md:max-w-md lg:max-w-lg h-auto mt-8 lg:mt-0" />
      </div>
      
    </div>
  );
};

export default HeroSection;
