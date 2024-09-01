import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import OrgSearchForm from './components/OrgSearchForm';
import SearchPageTest from './test/SearchPageTest';
import SearchPage from './pages/SearchPage';

function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<SearchPage />} />
        <Route path="/search" element={<OrgSearchForm onSearch={() => {}} />} /> {/* Ajuste o onSearch conforme necessário */}
      </Routes>
    </Router>
  );
}

export default App;
