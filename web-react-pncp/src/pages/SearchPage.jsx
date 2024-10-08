import React, { useState, useRef } from 'react';
import Navbar from '../components/Navbar';
import OrgSearchForm from '../components/OrgSearchForm';
import ContractsList from '../components/ContractsList';
import ErrorModal from '../components/ErrorModal';
import SearchExplanation from '../components/SearchExplanation';
import { useOrgContracts } from '../hooks/useOrgContracts';
import { useOrgContractsTest } from '../hooks/useOrgContractsTest';
import HeroSection from '../components/HeroSection';
import AutoOpenModal from '../components/AutoOpenModal';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import '../styles/variables.css';

const SearchPage = () => {

  const [isTestLocal, setIsTestLocal] = useState(false);  // Estado para o toggle
  const { contracts, orgao, totalValue, searchContracts } = isTestLocal ? useOrgContractsTest() : useOrgContracts(); // Usar o hook correto com base no toggle
  const [searchPerformed, setSearchPerformed] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');
  const [isErrorModalVisible, setIsErrorModalVisible] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(true);  // Estado para controlar a visibilidade do modal

  const contractsListRef = useRef(null); // Ref para ContractsList

  const handleScrollToSection = () => {
    if (contractsListRef.current) {
      const offset = window.innerHeight * 0.10; // 10% da altura da janela
      const sectionPosition = contractsListRef.current.getBoundingClientRect().top + window.pageYOffset - offset;
      
      window.scrollTo({
        top: sectionPosition,
        behavior: 'smooth'
      });
    }
  };

  const handleSearch = async (cnpj, dataIni, dataFim) => {
    try {
      if (isTestLocal) {
        await searchContracts(cnpj);  // Chama a função do hook de teste
      } else {
        await searchContracts(cnpj, dataIni, dataFim);  // Chama a função do hook normal
      }
      setSearchPerformed(true);

      // Rola até a seção de ContractsList quando a pesquisa é bem-sucedida
      if (contractsListRef.current) {
        
        handleScrollToSection();    

      }
    
    } catch (error) {

      setErrorMessage(error.message);
      setIsErrorModalVisible(true);

    }
  };

  const closeErrorModal = () => {

    setIsErrorModalVisible(false);
    setErrorMessage('');
    
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
  };

  const triggerModal = () => {
    setIsModalOpen(true); // Reabre o modal quando o usuário clica em "Aviso"
  };

  return (
    <div className="min-h-screen">
      
      <Navbar triggerModal={triggerModal} />

      <HeroSection />

      <SearchExplanation/>

      <div className="flex items-center justify-center">
        <OrgSearchForm onSearch={handleSearch} setIsTestLocal={setIsTestLocal} />
      </div>

      {searchPerformed && contracts.length > 0 && (
        <div ref={contractsListRef} className="w-full max-w-5xl mx-auto mb-10">  {/* Ref adicionada aqui */}
          <ContractsList orgao={orgao} contracts={contracts} totalValue={totalValue} />
        </div>
      )}

      {isErrorModalVisible && (
        <ErrorModal message={errorMessage} onClose={closeErrorModal} />
      )}

      <AutoOpenModal isOpen={isModalOpen} onClose={handleCloseModal} />

      <ToastContainer
        style={{ marginTop: '80px' }}
        autoClose={5000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
      />
    </div>
  );
};

export default SearchPage;
