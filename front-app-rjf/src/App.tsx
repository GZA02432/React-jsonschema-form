import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
//import 'bootswatch/dist/cerulean/bootstrap.min.css';
import FormList from './components/FormList';
import DynamicForm from './components/DynamicForm';

const App: React.FC = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<FormList />} />
        <Route path="/forms/:id" element={<DynamicForm />} />
        <Route path="*" element={<div>ページが見つかりません。</div>} />
      </Routes>
    </Router>
  );
};

export default App;