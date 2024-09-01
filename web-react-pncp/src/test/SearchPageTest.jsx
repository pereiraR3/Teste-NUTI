import React, { useState } from 'react';
import Navbar from '../components/Navbar';
import OrgSearchForm from '../components/OrgSearchForm';
import ContractsListTest from '../test/ContractsListTest';
import ErrorModal from '../components/ErrorModal';
import { useOrgContractsTest } from './useOrgContractsTest';
import HeroSection from '../components/HeroSection';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import '../styles/variables.css';


const SearchPageTest = () => {
    const { contracts, orgao, totalValue, searchContracts } = useOrgContractsTest();
    const [searchPerformed, setSearchPerformed] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');
    const [isErrorModalVisible, setIsErrorModalVisible] = useState(false);
  
    const handleSearch = async (cnpj, dataIni, dataFim) => {
      try {
        await searchContracts(cnpj, dataIni, dataFim);
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
  
    return (
      <div className="min-h-screen">
  
        <Navbar />
  
        <HeroSection />
  
        <div className="flex items-center justify-center mt-4">
          <OrgSearchForm onSearch={handleSearch} />
        </div>
  
        {searchPerformed && contracts.length > 0 && (
          <div className="flex items-center justify-center mb-10">
            <ContractsListTest orgao={orgao} contracts={contracts} totalValue={totalValue} />
          </div>
        )}
        
        {isErrorModalVisible && (
          <ErrorModal message={errorMessage} onClose={closeErrorModal} />
        )}
  
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
  
  export default SearchPageTest;