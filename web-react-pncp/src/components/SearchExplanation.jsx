// SearchExplanation.jsx
import React from 'react';
import { AiOutlineCheckCircle } from 'react-icons/ai'; // Ícone de círculo com check da biblioteca react-icons

const SearchExplanation = () => {
  return (
    <div id="searchExplanation" className="bg-gradient-to-r py-12 px-6 sm:px-10 lg:px-16 rounded-lg shadow-lg relative">
      {/* Adiciona uma sombra nas bordas superior e inferior para efeito de profundidade */}
      <div className="absolute inset-x-0 top-0 h-2 bg-gradient-to-b from-blue-300/50 to-transparent shadow-xl"></div>

      {/* Centraliza o conteúdo dentro de um contêiner de largura máxima */}
      <div className="max-w-4xl mx-auto text-center">
        
        {/* Título da seção, com estilo responsivo para tamanhos de texto */}
        <h2 className="text-3xl sm:text-4xl font-extrabold text-blue-800 mb-8">
          Como Funciona a Pesquisa
        </h2>

        {/* Texto introdutório explicando os passos da pesquisa */}
        <p className="text-gray-600 text-lg sm:text-xl mb-8 leading-relaxed">
          Siga os passos abaixo para realizar uma pesquisa eficiente no portal:
        </p>

        {/* Lista não ordenada com os passos explicativos da pesquisa */}
        <ul className="space-y-6 text-gray-800 text-lg sm:text-xl text-left">
          {/* Cada item da lista contém um ícone e uma descrição do passo */}
          
          {/* Passo 1: Inserir o CNPJ do órgão */}
          <li className="flex items-start transition-transform transform hover:scale-105">
            <AiOutlineCheckCircle className="text-blue-600 text-2xl mr-4 mt-1" /> {/* Ícone de check */}
            <p>
              <span className="font-semibold">Inserir o CNPJ do órgão:</span> Caso não tenha o CNPJ em mãos, pesquise na internet para localizá-lo rapidamente.
            </p>
          </li>

          {/* Passo 2: Adicionar data de início e data de fim */}
          <li className="flex items-start transition-transform transform hover:scale-105">
            <AiOutlineCheckCircle className="text-blue-600 text-2xl mr-4 mt-1" /> {/* Ícone de check */}
            <p>
              <span className="font-semibold">Adicionar a data de início e data de fim:</span> Insira as datas corretamente para delimitar o período de análise.
            </p>
          </li>

          {/* Passo 3: Verificação do período inserido */}
          <li className="flex items-start transition-transform transform hover:scale-105">
            <AiOutlineCheckCircle className="text-blue-600 text-2xl mr-4 mt-1" /> {/* Ícone de check */}
            <p>
              <span className="font-semibold">Verificação de período:</span> Se o período for superior a 365 dias ou se as datas estiverem incorretas, a análise não poderá ser realizada.
            </p>
          </li>

          {/* Passo 4: Modo de Teste */}
          <li className="flex items-start transition-transform transform hover:scale-105">
            <AiOutlineCheckCircle className="text-blue-600 text-2xl mr-4 mt-1" /> {/* Ícone de check */}
            <p>
              <span className="font-semibold">Utilizando o Modo de Teste:</span> Ao acionar o toggle de teste, a pesquisa utilizará dados já persistidos no banco de dados, permitindo uma análise rápida sem necessidade de inserir novas datas.
            </p>
          </li>
        </ul>
      </div>

      {/* Adiciona uma sombra na parte inferior da caixa */}
      <div className="absolute inset-x-0 bottom-0 h-2 bg-gradient-to-t from-blue-300/50 to-transparent shadow-xl"></div>
    </div>
  );
};

export default SearchExplanation;
