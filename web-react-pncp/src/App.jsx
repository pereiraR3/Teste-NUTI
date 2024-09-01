import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import OrgSearchForm from './components/OrgSearchForm';
import SearchPage from './pages/SearchPage';
import PageMeta from './components/PageMeta';

function App() {
  return (
    <Router>
      
      {/* Coloque o PageMeta aqui, fora das Routes */}
      <PageMeta title="Consulta PNCP" favicon="../public/bandeira_brasil.png" />
      
      {/* Navbar fora das Routes para aparecer em todas as páginas */}
      <Navbar />
      
      <Routes>
        {/* Definição das rotas */}
        <Route path="/" element={<SearchPage />} />
        <Route path="/search" element={<OrgSearchForm onSearch={() => {}} />} />
      </Routes>
    </Router>
  );
}

export default App;
