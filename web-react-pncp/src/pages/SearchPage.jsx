import React, { useState } from 'react';
import Navbar from '../components/Navbar';
import OrgSearchForm from '../components/OrgSearchForm';
import ContractsList from '../components/ContractsList';
import ErrorModal from '../components/ErrorModal';
import { useOrgContracts } from '../hooks/useOrgContracts';
import { useOrgContractsTest } from '../test/useOrgContractsTest';
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

  const handleSearch = async (cnpj, dataIni, dataFim) => {
    try {
      if (isTestLocal) {
        await searchContracts(cnpj);  // Chama a função do hook de teste
      } else {
        await searchContracts(cnpj, dataIni, dataFim);  // Chama a função do hook normal
      }
      setSearchPerformed(true);
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

  return (
    <div className="min-h-screen">
      <Navbar />
      <HeroSection />
      <div className="flex items-center justify-center mt-4">
        <OrgSearchForm onSearch={handleSearch} setIsTestLocal={setIsTestLocal} />
      </div>

      {searchPerformed && contracts.length > 0 && (
        <div className="flex items-center justify-center mb-10">
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
